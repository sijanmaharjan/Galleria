package app;

import bean.entity.Image;

import java.util.Arrays;
import java.util.List;

public interface MockImages {
    static List<Image> get(){
        return Arrays.asList(
                new Image("Sunset", "Sunset", "sunset.jpg"),
                new Image("Bike", "Bike", "bike.jpg"),
                new Image("Mountain", "Mountain", "mountain.jpg")
        );
    }
}
