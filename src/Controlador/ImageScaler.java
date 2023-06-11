/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import java.awt.image.BufferedImage;
import java.awt.Image;
/**
 *
 * @author monta
 */
public class ImageScaler {
    public static BufferedImage scaleImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage bufferedScaledImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        bufferedScaledImage.getGraphics().drawImage(scaledImage, 0, 0, null);
        return bufferedScaledImage;
    }
}
