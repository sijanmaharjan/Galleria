package controller.service;

import bean.entity.Image;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

public interface ImageFileService {
    void uploadFile(HttpServletRequest request, Image image, Part filePart) throws IOException;
    void toggleWatermark(HttpServletRequest request, Image image) throws IOException;
    void addWatermark(String in, String out);
    void removeFile(HttpServletRequest request, Image image);
    void removeFiles(HttpServletRequest request, List<Image> images);
}
