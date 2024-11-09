package top.atluofu.tomcat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName: Response
 * @description: Response
 * @author: 有罗敷的马同学
 * @datetime: 2024Year-11Month-09Day-11:21
 * @Version: 1.0
 */
public class Response {
    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        File file = new File(HttpServer.WEB_ROOT, request.getUri());
        if (!file.exists()) {
            String errorMessage = "Http / 1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n"
                    + "\r\n"
                    + "<h1>404 File Not Found</h1>";
            output.write(errorMessage.getBytes());
            return;
        }
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            int read = fileInputStream.read(bytes, 0, BUFFER_SIZE);
            while (read != -1) {
                output.write(bytes, 0, read);
                read = fileInputStream.read(bytes, 0, BUFFER_SIZE);
            }
        }

    }
}
