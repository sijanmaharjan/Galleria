package controller.service;

import java.awt.image.BufferedImage;

public class ThumbnailServiceImpl implements ThumbnailService {

    @Override
    public BufferedImage createThumbnail(BufferedImage source, double ratio) {
        int w = (int) Math.floor(source.getWidth() * ratio);
        int h = (int) Math.floor(source.getHeight() * ratio);
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        img.createGraphics().drawImage(source.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH),0,0,null);
        return img;
    }
}
