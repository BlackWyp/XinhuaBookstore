package tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Tool {
    // 给ajax请求返回json格式的数据
    public static void returnIntResult(HttpServletResponse response, Integer result)
            throws ServletException, IOException {
        // response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write("{\"result\":" + result.toString() + "}");
        out.flush();
    }

    // 给ajax请求返回json格式的数据
    static public void returnJsonString(HttpServletResponse response, String jsonString)
            throws ServletException, IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(jsonString);
        out.flush();
    }
}
