package controller.servlet.ia;

import app.AppContext;
import bean.entity.Image;
import bean.remote.ImageBeanRemote;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(urlPatterns = "/galleria.download(image)")
public class ImageDownloadServlet extends HttpServlet {

    private static final String IMAGE_DIR = "images";
    private static final String THUMB_DIR = "thumbnails";

    @EJB
    private ImageBeanRemote imageBeanRemote;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AppContext appContext = AppContext.parseContext(req.getServletContext());
        String IID = req.getParameter("id");
        Image image = imageBeanRemote.get(IID);
        if(image != null){
            String applicationPath = req.getServletContext().getRealPath("");
            String imagePath = applicationPath + File.separator + IMAGE_DIR;
            String thumbPath = applicationPath + File.separator + THUMB_DIR;

            boolean ownedOrFree = imageBeanRemote.isOwner(IID, appContext.getUserID()) || imageBeanRemote.isAccessible(IID, appContext.getUserID());
            // reads input file from an absolute path
            String filePath = ownedOrFree? imagePath+File.separator+image.getSource() : thumbPath+File.separator+image.getSource();
            File downloadFile = new File(filePath);
            FileInputStream inStream = new FileInputStream(downloadFile);

            // if you want to use a relative path to context root:
            String relativePath = getServletContext().getRealPath("");

            // obtains ServletContext
            ServletContext context = getServletContext();

            // gets MIME type of the file
            String mimeType = context.getMimeType(filePath);
            if (mimeType == null) {
                // set to binary type if MIME mapping not found
                mimeType = "application/octet-stream";
            }
            System.out.println("MIME type: " + mimeType);

            // modifies response
            resp.setContentType(mimeType);
            resp.setContentLength((int) downloadFile.length());

            // forces download
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", image.getTitle()+".jpeg");
            resp.setHeader(headerKey, headerValue);

            // obtains response's output stream
            OutputStream outStream = resp.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            inStream.close();
            outStream.close();
        }else{
            resp.sendError(404);
        }
    }
}
