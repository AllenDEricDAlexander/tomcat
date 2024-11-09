package top.atluofu.tomcat;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName: HttpServer
 * @description: HttpServer
 * @author: 有罗敷的马同学
 * @datetime: 2024Year-11Month-09Day-11:10
 * @Version: 1.0
 */
public class HttpServer {
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.await();
    }

    public void await() {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));) {
            // loop waiting for a request
            while (!shutdown) {
                Socket socket = null;
                InputStream inputStream = null;
                OutputStream outputStream = null;
                try {
                    socket = serverSocket.accept();
                    inputStream = socket.getInputStream();
                    outputStream = socket.getOutputStream();
                    Request request = new Request(inputStream);
                    request.parse();
                    Response response = new Response(outputStream);
                    response.setRequest(request);
                    response.sendStaticResource();

                    socket.close();

                    shutdown = request.getUri().equals(SHUTDOWN_COMMAND);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
