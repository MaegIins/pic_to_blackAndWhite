import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import javax.imageio.ImageIO;


public class transfo {
    
      
    public static void monochrome(String fileName, String newFile) throws IOException {

        File input = new File(fileName);
        BufferedImage image = ImageIO.read(input);
    
        BufferedImage result = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_BYTE_BINARY);
    
        Graphics2D graphic = result.createGraphics();
        graphic.drawImage(image, 0, 0, null, null);
        graphic.dispose();
    
        File output = new File(newFile+"monochrome.png");
        ImageIO.write(result, "png", output);
    
    
    }
    

    public static void bwg(String fileName, String newFile) throws IOException {
    
      File file = new File(fileName);
        BufferedImage orginalImage = ImageIO.read(file);
    
        BufferedImage blackAndWhiteImg = new BufferedImage(
            orginalImage.getWidth(), orginalImage.getHeight(),
            BufferedImage.TYPE_USHORT_GRAY);
    
        Graphics2D graphics = blackAndWhiteImg.createGraphics();
        graphics.drawImage(orginalImage, 0, 0, null);
    
        ImageIO.write(blackAndWhiteImg, "png", new File(newFile+"bwg.png"));
    }
    
}