package test;


import static org.junit.Assert.assertNotNull;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;


class ImageTest {

    @Test
    void test() {

        try {
            BufferedImage image = ImageIO
                .read(new File("/Users/pingjincheng/git/repository/mytank/src/images/bulletD.gif"));
            assertNotNull(image);

            BufferedImage images = ImageIO
                .read(ImageTest.class.getClassLoader().getResource("images/bulletD.gif"));
            assertNotNull(images);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
