package top.atluofu.tomcat;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * @ClassName: ResponseFacade
 * @description: ResponseFacade
 * @author: 有罗敷的马同学
 * @datetime: 2024Year-11Month-10Day-16:05
 * @Version: 1.0
 */
public class ResponseFacade implements ServletResponse {
    private ServletResponse response;

    public ResponseFacade(Response response) {
        this.response = response;
    }

    public void flushBuffer() throws IOException {
        response.flushBuffer();
    }

    public int getBufferSize() {
        return response.getBufferSize();
    }

    public String getCharacterEncoding() {
        return response.getCharacterEncoding();
    }

    @Override
    public String getContentType() {
        return "";
    }

    public Locale getLocale() {
        return response.getLocale();
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return response.getOutputStream();
    }

    public PrintWriter getWriter() throws IOException {
        return response.getWriter();
    }

    @Override
    public void setCharacterEncoding(String s) {

    }

    public boolean isCommitted() {
        return response.isCommitted();
    }

    public void reset() {
        response.reset();
    }

    public void resetBuffer() {
        response.resetBuffer();
    }

    public void setBufferSize(int size) {
        response.setBufferSize(size);
    }

    public void setContentLength(int length) {
        response.setContentLength(length);
    }

    @Override
    public void setContentLengthLong(long l) {

    }

    public void setContentType(String type) {
        response.setContentType(type);
    }

    public void setLocale(Locale locale) {
        response.setLocale(locale);
    }
}
