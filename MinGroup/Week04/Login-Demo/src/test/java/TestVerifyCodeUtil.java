package test.java;

import com.lldw.www.utils.VerifyCodeUtil;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author
 * @date
 */
public class TestVerifyCodeUtil {

    @Test
    public void test() throws IOException {
        VerifyCodeUtil code = new VerifyCodeUtil();
        BufferedImage image = code.createImage();
        ImageIO.write(image,"jpg",new File("E:/image.jpg"));
    }
}
