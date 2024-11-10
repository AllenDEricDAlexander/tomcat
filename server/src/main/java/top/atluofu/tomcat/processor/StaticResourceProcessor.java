package top.atluofu.tomcat.processor;

import top.atluofu.tomcat.Request;
import top.atluofu.tomcat.Response;

/**
 * @ClassName: StaticResourceProcessor
 * @description: StaticResourceProcessor
 * @author: 有罗敷的马同学
 * @datetime: 2024Year-11Month-10Day-15:42
 * @Version: 1.0
 */
public class StaticResourceProcessor {
    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
