package com.lldw.www.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @author I don't know
 * @date 2023-04-11 15:17:34
 */
public class VerifyCodeUtil {

    private int w = 70;
    private int h = 35;

    private Random r = new Random();
    // 定义有那些字体
    private String[] fontNames = { "宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312" };
    // 定义有那些验证码的随机字符
    private String codes = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ";
    // 生成背景色
//    private Color bgColor = new Color(250, 250, 250);
    private Color bgColor = new Color(250, 250, 250);
    // 用于gettext 方法 获得生成的验证码文本
    private String text;

    // 生成随机颜色
    private Color randomColor() {
        int red = r.nextInt(150);
        int green = r.nextInt(150);
        int blue = r.nextInt(150);
        return new Color(red, green, blue);
        //255 240 245 粉色
//        return new Color(255,240, 245);
    }

    // 生成随机字体
    private Font randomFont() {
        int index = r.nextInt(fontNames.length);
        String fontName = fontNames[index];
        int style = r.nextInt(4);
        int size = r.nextInt(5) + 24;

        return new Font(fontName, style, size);
    }

    // 画干扰线
    private void drawLine(BufferedImage image) {
        int num = 3;
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        for (int i = 0; i < num; i++) {
            int x1 = r.nextInt(w);
            int y1 = r.nextInt(h);
            int x2 = r.nextInt(w);
            int y2 = r.nextInt(h);
            g2.setStroke(new BasicStroke(1.5F));// 不知道
            g2.setColor(Color.blue);
            g2.drawLine(x1, y1, x2, y2);
        }
    }

    // 得到codes的长度内的随机数 并使用charAt 取得随机数位置上的codes中的字符
    private char randomChar() {
        int index = r.nextInt(codes.length());
        return codes.charAt(index);
    }

    // 创建一张验证码的图片
    public BufferedImage createImage() {
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);



        //Graphics是java提供的一个用于绘图和显示表格格式化文字的一个工具，就是一个画笔
        //Graphics中的setColor方法将文字、边框或要填充的区域为指定的颜色
        Graphics graphics = image.getGraphics();

        //设置颜色：setColor(new Color(int r, int g, int b));
        //RGB颜色设置方法，RGB是红、绿、蓝三个颜色，每个颜色强度是0~255,由红、绿、蓝按照不同比例混合就能产生一种颜色，
        graphics.setColor(new Color(255, 255, 255));

        //填充图片：fillRect(int x,int y, int width,int height);
        //其中x代表距矩形左边缘的位置，y是代表距上边缘的位置，width代表图片的宽度，height代表高度
        graphics.fillRect(0, 0, w, h);
        //释放资源
        graphics.dispose();


        Graphics2D g2 = (Graphics2D) image.getGraphics();
        StringBuilder sb = new StringBuilder();
        // 向图中画四个字符
        for (int i = 0; i < 4; i++) {
            String s = randomChar() + "";
            sb.append(s);
            float x = i * 1.0F * w / 4;
            g2.setFont(randomFont());
            g2.setColor(randomColor());
            g2.drawString(s, x, h - 5);

        }
        this.text = sb.toString();
        drawLine(image);

        // 返回图片
        return image;

    }

    // 得到验证码的文本 后面是用来和用户输入的验证码 检测用
    public String getText() {
        return text;
    }

    // 定义输出的对象和输出的方向
    public static void output(BufferedImage bi, OutputStream fos)
            throws FileNotFoundException, IOException {
        ImageIO.write(bi, "JPEG", fos);
    }
}
