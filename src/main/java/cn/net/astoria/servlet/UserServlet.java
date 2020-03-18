package cn.net.astoria.servlet;

import cn.net.astoria.domain.Boss;
import cn.net.astoria.domain.ResultInfo;
import cn.net.astoria.service.UserService;
import cn.net.astoria.service.impl.UserServiceImpl;
import org.omg.IOP.ComponentIdHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.ws.spi.Invoker;
import java.io.IOException;

/**
 * @ClassName ${UserServlet}
 * @Description 处理管理员相关功能的Servlet
 * @Author Astoria
 * @Date 2020/3/2 11:17
 * @Version 1.0
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    /**
     * 管理员的Service
     */
    private UserService userService = new UserServiceImpl();

    /**
     * 用于管理员登录
     * @param request
     * @param response
     */
    public void login(HttpServletRequest request,HttpServletResponse response){
//        处理用户登录的请求
//        首先获取用户提交的信息
        String userPostUserName = request.getParameter("username");
        String userPostPassword = request.getParameter("password");
        String userPostCheckCode = request.getParameter("code");
        ResultInfo resultInfo = new ResultInfo();


//        首先检测用户提交的验证码是否正确
        String servlerCode = (String) request.getSession().getAttribute("checkCode");
        if(userPostCheckCode!=null && servlerCode!=null && servlerCode.equalsIgnoreCase(userPostCheckCode)){
//            用户提交的验证码正确 接下来对用户的用户名和密码进行验证
            if(userPostUserName !=null && userPostPassword != null){
//                用户提交的信息有效
                Boss getBoss = userService.checkUsernameAndPassword(userPostUserName,userPostPassword);
                if(getBoss == null){
//                    没有查询到相关信息 用户名或者密码错误
                    resultInfo.setFlag(false);
                    resultInfo.setErrorMessage("用户名或者密码错误");
                }else{
//                    查询到结果  表示用户名和密码输入正确
//                    将Boss写入到Session中 以此来标记用户已经登录
                    request.getSession().setAttribute("boss", getBoss);
//                    设置Session的失效时间为4个小时
                    request.getSession().setMaxInactiveInterval(60*60*4);
                    resultInfo.setFlag(true);
//                    如果登录成功 将跳转页面发送给客户端 让客户端跳转到后台主页
                    resultInfo.setData(new String("index.html"));
                }
            }else{
//                用户提交的信息无效
                resultInfo.setFlag(false);
                resultInfo.setErrorMessage("用户名或者密码错误");
            }

        }else{
//            用户提交的验证码错误
            resultInfo.setFlag(false);
            resultInfo.setErrorMessage("验证码填写错误");
        }
//        清空用户提交的验证码 防止用户后退到登录页面的时候验证码依旧有效
        request.getSession().removeAttribute("checkCode");
//        给客户端相应
        writeObject(resultInfo, response);

    }

    /**
     * 用于获取已经登录用户的用户名
     * @param request
     * @param response
     */
    public void getUsername(HttpServletRequest request,HttpServletResponse response){
//        获取用户的Boss信息
        Boss boss = (Boss)request.getSession().getAttribute("boss");
        ResultInfo resultInfo = new ResultInfo();
        if(boss!=null){
            resultInfo.setFlag(true);
            resultInfo.setData(boss.getB_name());
        }else{
//            如果信息丢失 要求用户重新登录
            resultInfo.setFlag(false);
            resultInfo.setErrorMessage("登录信息丢失，请重新登录");
        }
        writeObject(resultInfo, response);
    }


    /**
     * 用于对管理员进行二次确认，用于高危权限操作
     * @param request
     * @param response
     */
    public void testUser(HttpServletRequest request,HttpServletResponse response){
//        获取用户提交的密码
        String pwStr = request.getParameter("pw");
        ResultInfo resultInfo = new ResultInfo();
        if(testParameter(pwStr)){
            Boss boss = (Boss)request.getSession().getAttribute("boss");
            if(boss.getB_password().equals(pwStr)){
                resultInfo.setFlag(true);
                resultInfo.setData("waiterEdit.html");
            }else{
                resultInfo.setFlag(false);
                resultInfo.setErrorMessage("认证失败，请重试");
            }
        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMessage("认证失败，请重试");
        }
        writeObject(resultInfo, response);
    }
}
