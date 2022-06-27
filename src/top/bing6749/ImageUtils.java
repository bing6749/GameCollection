package top.bing6749;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * @author MaRui
 */
public class ImageUtils {
    public static Image getImage(String path) {
        Image img = null;
        URL url = ImageUtils.class.getClassLoader().getResource(path);
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
