package controller.service;

import java.awt.image.BufferedImage;

public interface ThumbnailService {

    BufferedImage createThumbnail(BufferedImage source, double ratio);

    static float getCarouselRatio(float width, float height){
        if(height < width){
            if(height > 463) return 463/height;
            else return 1;
        }else{
            if(width > 825) return 825/width;
            else return 1;
        }
    }
}
