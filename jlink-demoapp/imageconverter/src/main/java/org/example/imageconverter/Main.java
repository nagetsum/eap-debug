package org.example.imageconverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];

        try {
            BufferedImage inputImage = ImageIO.read(new File(inputFile));
            ImageIO.write(inputImage, "png", new File(outputFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
