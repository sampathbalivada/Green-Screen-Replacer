import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
 
public class ImageProcess
{
    public static void main(String args[])throws IOException
    {
        BufferedImage img = null, imgBkgPrimary = null, imgBkg = null;
        File f = null;
 
        // read images with subject, subject background and new background
        try
        {       
            f = new File("img.jpg");
            img = ImageIO.read(f);
            f = new File("bkgPrimary.jpg");
            imgBkgPrimary = ImageIO.read(f);
            f = new File("bkgSecondary.jpg");
            imgBkg = ImageIO.read(f);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
 
        // get image's width and height
        int width = img.getWidth();
        int height = img.getHeight();
 
        // change background
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                // Here (x,y)denotes the coordinate of image 

                //get pixel data from image with the subject
                int p1 = img.getRGB(x,y);

                //get pixel data from image with the background
                int p2 = imgBkgPrimary.getRGB(x,y);

                //Check if both the pixels are equal
                if(p1 == p2) {

                    /*if(equal) -> read pixel data from new background image at the 
                    /*same position and overwrite the pixel data in image with subject*/
                    int p3 = imgBkg.getRGB(x,y);
                    p1 = p3;
                    img.setRGB(x, y, p1);

                }

            }
        }
 
        // write image
        try
        {
            f = new File("out.jpg");
            ImageIO.write(img, "jpg", f);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
}
