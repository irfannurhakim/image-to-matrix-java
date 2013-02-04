/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixcompare;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author irfannurhakim
 */
public class MatrixCompare {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        if(args.length < 2){
            System.out.println("Required Params");
            
            System.exit(0);
        }
        
        
        File file = new File(args[0]);
        File file1 = new File(args[1]);


        try {
            Image image = ImageIO.read(file);
            Image image1 = ImageIO.read(file1);
            
            
            int[][] matricesA = new int[image.getWidth(null)][image.getHeight(null)];
            int[][] matricesB = new int[image.getWidth(null)][image.getHeight(null)];
            
            if((image.getWidth(null) != image1.getWidth(null)) || image.getHeight(null) != image1.getHeight(null)){
                System.out.println("Size not equal");
                System.exit(0);
            }
            
            handlepixels(image, 0, 0, image.getWidth(null), image.getHeight(null), matricesA);
            handlepixels(image1, 0, 0, image1.getWidth(null), image1.getHeight(null), matricesB);
            
            System.out.println("Total Pixel : " + image.getWidth(null) * image.getHeight(null));
            System.out.println("Unmatch Pixel : " + compare(matricesA, matricesB));
        } catch (IOException ex) {
            Logger.getLogger(MatrixCompare.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static int handlesinglepixel(int x, int y, int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;

        if ((red + green + blue) > (255 * 3 / 2)) {
            return 255;
        } else {
            return 0;
        }
    }

    private static void handlepixels(Image img, int x, int y, int w, int h, int[][] matrices) {
        int[] pixels = new int[w * h];
        PixelGrabber pg = new PixelGrabber(img, x, y, w, h, pixels, 0, w);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
            System.err.println("interrupted waiting for pixels!");
            return;
        }
        if ((pg.getStatus() & ImageObserver.ABORT) != 0) {
            System.err.println("image fetch aborted or errored");
            return;
        }
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                matrices[i][j] = handlesinglepixel(x + i, y + j, pixels[j * w + i]);
            }
        }
    }
    
    private static int compare(int[][] a, int[][] b){
        int err = 0;

        for(int j=0; j < a.length; j++){
            for(int i=0; i < a[j].length; i++){
                //System.out.println("a["+j+"]"+"["+i+"]="+a[i][j]+" | " + "b["+j+"]"+"["+i+"]="+b[i][j]);
                if(a[j][i] != b[j][i]){
                    err += 1;
                }
            }
        }
        
        return err;
    }
}
