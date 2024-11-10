package top.atluofu.tomcat.server;

import top.atluofu.tomcat.Request;
import top.atluofu.tomcat.Response;
import top.atluofu.tomcat.processor.ServletProcessor1;
import top.atluofu.tomcat.processor.StaticResourceProcessor;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName: HttpServer1
 * @description: HttpServer1
 * @author: 有罗敷的马同学
 * @datetime: 2024Year-11Month-10Day-15:35
 * @Version: 1.0
 */
public class HttpServer1 {
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer1 httpServer = new HttpServer1();
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
                    if (request.getUri().startsWith("/servlet")) {
                        ServletProcessor1 servletProcessor1 = new ServletProcessor1();
                        servletProcessor1.process(request, response);
                    } else {
                        StaticResourceProcessor staticResourceProcessor = new StaticResourceProcessor();
                        staticResourceProcessor.process(request, response);
                    }

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
