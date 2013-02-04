/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matrixcompare.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author irfannurhakim
 */
public class Transformer {
    
    private File img;
    private Image image;
    
    public Transformer(File img){
        this.img = img;
    }
    
    public void toMatrix() throws IOException{
        BufferedImage myImg = ImageIO.read(this.img);
    }
    
    
    
    
    
}
