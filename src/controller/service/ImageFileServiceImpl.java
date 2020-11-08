package controller.service;

import bean.entity.Image;
import com.groupdocs.watermark.Watermarker;
import com.groupdocs.watermark.watermarks.Color;
import com.groupdocs.watermark.watermarks.FontStyle;
import com.groupdocs.watermark.watermarks.TextAlignment;
import com.groupdocs.watermark.watermarks.TextWatermark;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ImageFileServiceImpl implements ImageFileService {

    private static final String IMAGE_DIR = "images";
    private static final String THUMB_DIR = "thumbnails";
    private static final String IMG_EXTENSION = ".jpeg";

    private ThumbnailService thumbnailService;

    public ImageFileServiceImpl() {
    }

    public ImageFileServiceImpl(ThumbnailService thumbnailService) {
        this.thumbnailService = thumbnailService;
    }

    @Override
    public void uploadFile(HttpServletRequest request, Image image, Part filePart) throws IOException {
        if(thumbnailService == null) throw new UnsupportedOperationException("ThumbnailService is not set yet!");
        File imagePath = getImageFile(getApplicationPath(request), "");
        File thumbPath = getThumbFile(getApplicationPath(request), "");
        createDirectory(imagePath);
        createDirectory(thumbPath);
        InputStream filecontent = filePart.getInputStream();
        BufferedImage bufferedImage = ImageIO.read(filecontent);
        String filename = getFileName(image);
        ImageIO.write(bufferedImage, "jpg", new File(imagePath, filename));
        BufferedImage thumbnail = thumbnailService.createThumbnail(bufferedImage, ThumbnailService.getCarouselRatio(bufferedImage.getWidth(), bufferedImage.getHeight()));
        ImageIO.write(thumbnail, "jpg", new File(thumbPath, filename));
        if(image.getPrice() > 0){
            addWatermark(getThumbPath(filename), getThumbPath(filename));
        }
    }

    public void toggleWatermark(HttpServletRequest request, Image image) throws IOException {
        if(thumbnailService == null) throw new UnsupportedOperationException("ThumbnailService is not set yet!");
        String fileName = getFileName(image);
        File img = getImageFile(getApplicationPath(request), fileName);
        File thumb = getThumbFile(getApplicationPath(request), fileName);
        thumb.delete();
        BufferedImage bufferedImage = ImageIO.read(img);
        BufferedImage thumbnail = thumbnailService.createThumbnail(bufferedImage, ThumbnailService.getCarouselRatio(bufferedImage.getWidth(), bufferedImage.getHeight()));
        ImageIO.write(thumbnail, "jpg", thumb );
        if (image.getPrice() > 0) {
            addWatermark(thumb.toString(), thumb.toString());
        }
    }

    @Override
    public void addWatermark(String in, String out) {
        TextWatermark watermark = new TextWatermark("Galleria", new com.groupdocs.watermark.watermarks.Font("Arial", 30, FontStyle.Bold | FontStyle.Italic));
        watermark.setForegroundColor(Color.getBlack());
        watermark.setTextAlignment(TextAlignment.Right);
        watermark.setRotateAngle(-30);
        watermark.setOpacity(0.4);
        watermark.setX(70);
        watermark.setY(70);
        Watermarker watermarker = new Watermarker(in);
        watermarker.add(watermark);
        watermarker.save(out);
        watermarker.close();
    }

    @Override
    public void removeFile(HttpServletRequest request, Image image) {
        String applicationPath = getApplicationPath(request);
        removeFile(applicationPath, image);
    }

    @Override
    public void removeFiles(HttpServletRequest request, List<Image> images) {
        String applicationPath = getApplicationPath(request);
        images.forEach(image -> removeFile(applicationPath, image));
    }

    public void removeFile(String applicationPath, Image image){
        String fileName = getFileName(image);
        deleteFile(getImagePath(applicationPath), fileName);
        deleteFile(getThumbPath(applicationPath), fileName);
    }

    public void deleteFile(String path, String fileName){
        File file = getFile(path, fileName);
        if(file.exists()) file.delete();
    }

    public String getApplicationPath(HttpServletRequest request){
        return request.getServletContext().getRealPath("");
    }

    public String getImagePath(String applicationPath){
        return applicationPath + File.separator + IMAGE_DIR;
    }

    public String getThumbPath(String applicationPath){
        return applicationPath + File.separator + THUMB_DIR;
    }

    public File getImageFile(String applicationPath, String fileName){
        return getFile(getImagePath(applicationPath), fileName);
    }

    public File getThumbFile(String applicationPath, String fileName){
        return getFile(getThumbPath(applicationPath), fileName);
    }

    public String getFileName(Image image){
        return image.getId() + IMG_EXTENSION;
    }

    public File getFile(String path, String fileName){
        return new File(path, fileName);
    }

    public void createDirectory(File path){
        if (!path.exists()) {
            path.mkdirs();
        }
    }
}
