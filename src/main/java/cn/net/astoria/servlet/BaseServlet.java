package cn.net.astoria.servlet;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author ASTORIA
 */
public class BaseServlet extends HttpServlet {


    /**
     * 设置Mapper对象 用于将Java对象转换成Json并传输
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 获取请求的路径 并且根据路径通过反射调用相应Servlet中的方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取URI
        String requestURI = req.getRequestURI();
        //根据URI截取方法名称
        String methodName = requestURI.substring(requestURI.lastIndexOf("/") + 1);

        //通过反射调取方法
        try {
            //获取方法
            Method invokeMethod = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //调用方法
            invokeMethod.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
     * 向客户端浏览器以Json的形式发送Object对象
     * @param object
     * @param response
     */
    protected void writeObject(Object object,HttpServletResponse response){
        //设置数据类型
        response.setContentType("application/json;charset=utf-8;");
        //传输数据
        try {
            objectMapper.writeValue(response.getWriter(), object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用于检验从客户端浏览器接受到的数据是否有值
     * @param parameter
     * @return
     */
    protected boolean testParameter(String parameter){
        if(parameter == null || parameter.equalsIgnoreCase("")||parameter.equalsIgnoreCase("undefined")||parameter.length()==0){
            return false;
        }else{
            return true;
        }
    }
}
