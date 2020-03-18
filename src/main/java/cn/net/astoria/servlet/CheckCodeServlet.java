package cn.net.astoria.servlet;



import org.springframework.aop.target.LazyInitTargetSource;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.CollationElementIterator;
import java.util.Random;

/**
 * @author ASTORIA
 * 用于向客户端传送验证码
 */
@WebServlet(value = "/checkCode",name = "checkCode")
public class CheckCodeServlet extends HttpServlet {

    /**
     * 设置生成验证码的宽度
     */
    private final int WIDTH = 150;
    /**
     * 设置生成验证码的高度
     */
    private final int HEIGHT = 45;
    /**
     * 设置生成验证码的位数
     */
    private final int LENGTH = 4;
    /**
     * 设置生成验证码的字体大小
     */
    private final int FONTSIZE = 30;
    /**
     * 设置干扰线的数量
     */
    private final int LINECOUNT = 10;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        //填充背景色
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(0,0,WIDTH,HEIGHT);
        //设置边框
        graphics.setColor(Color.BLUE);
        graphics.drawRect(0,0, WIDTH-1, HEIGHT-1);
        //设置随机字符
        String checkCode = getRandomCode(LENGTH);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("黑体", Font.BOLD, FONTSIZE));
        for (int i=1;i<=checkCode.length();++i){
            graphics.drawString((String) checkCode.subSequence(i-1,i), ((WIDTH)/(LENGTH+1))*(i)-(FONTSIZE/2),(HEIGHT/2)+(FONTSIZE/2));
        }
        //设置干扰线
        graphics.setColor(Color.BLUE);
        Random random = new Random();
        for(int i=0;i<LINECOUNT;++i){
            graphics.drawLine(
                    random.nextInt(WIDTH),
                    random.nextInt(HEIGHT),
                    random.nextInt(WIDTH),
                    random.nextInt(HEIGHT));
        }
        //将验证码存储到Session中
        request.getSession().setAttribute("checkCode", checkCode);
        ImageIO.write(bufferedImage, "png", response.getOutputStream());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    private String getRandomCode(int length){
        StringBuilder stringBuilder = new StringBuilder();
        String randomString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        for (int i=0;i<length;++i){
            stringBuilder.append(randomString.charAt(random.nextInt(randomString.length())));
        }
        return stringBuilder.toString();
    }
}
