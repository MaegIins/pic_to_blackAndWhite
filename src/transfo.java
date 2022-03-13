    import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;



public class transfo {

    
public static boolean verifyFile(String s) {
    String ds = new String();
     ds += s.charAt(s.length()-4);
     ds += s.charAt(s.length()-3);
     ds += s.charAt(s.length()-2);
     ds += s.charAt(s.length()-1);


    if (ds.equals(".png") || ds.equals(".jpg") || ds.equals(".bmp")) {
        System.out.println("File is a "+ds);
        return true;

    }
    else {
        System.out.println("File is not a png, jpg or bmp, it is a "+ds);
        return false;
    }

}



      
    public static void monochrome(String fileName, String newFile) throws IOException {

        File file = new File(fileName);
        BufferedImage orginalImage = ImageIO.read(file);
        BufferedImage blackAndWhiteImg = new BufferedImage(
            orginalImage.getWidth(), orginalImage.getHeight(),
            BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D graphics = blackAndWhiteImg.createGraphics();
        graphics.drawImage(orginalImage, 0, 0, null);
        ImageIO.write(blackAndWhiteImg, "png", new File(newFile+"monochrome.png"));
        System.out.println("Monochrome image created");
    
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
        System.out.println("Black and white image created");
    }
    
}
