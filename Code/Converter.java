package com.example.springboot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Converter {
    static byte[] image;
    Convert(byte[] image){
        this.image = image;
    }
    public static byte[] Convert() throws IOException {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(image);
        BufferedImage newImage = ImageIO.read(byteStream);

        int width = newImage.getWidth();
        int height = newImage.getHeight();

        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                int p = newImage.getRGB(x,y);

                int a = (p>>24)&0xff;
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;

                int avg = (r+g+b)/3;

                p = (a<<24) | (avg<<16) | (avg<<8) | avg;
                newImage.setRGB(x,y,p);
            }
        }

        ByteArrayOutputStream fileByte = new ByteArrayOutputStream();
        ImageIO.write(newImage, "jpg", fileByte);
        byte[] outputBytes = fileByte.toByteArray();
        System.out.println(fileByte.getClass().getSimpleName());
     return outputBytes;
    }
}
