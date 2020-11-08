package controller.servlet.front.home.userview;

import app.AppContext;
import bean.remote.ImageBeanRemote;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserViewDispatcher {

    private final List<ImageView> viewList = new ArrayList<>();

    public UserViewDispatcher(ImageView ... views){
        viewList.addAll(Arrays.asList(views));
    }

    public void dispatch(HttpServletRequest req, HttpServletResponse resp, AppContext appContext, ImageRequest imageRequest) throws ServletException, IOException {
        String p = Optional.ofNullable(req.getParameter("p")).orElse("a");
        viewList.stream().filter(v -> v.getPageId().equals(p)).findFirst().orElse(getDefault()).doGet(req, resp, appContext, imageRequest);
    }

    private ImageView getDefault(){
        return viewList.get(0);
    }

    public static abstract class ImageView{

        protected final String jsp_file = "home/home.jsp";

        private final String pageId;

        public ImageView(String pageId) {
            this.pageId = pageId;
        }

        public String getPageId() {
            return pageId;
        }

        protected void doGet(HttpServletRequest req, HttpServletResponse resp, AppContext appContext, ImageRequest imageRequest) throws ServletException, IOException {}
    }

    public static class ImageRequest {
        private final String searchText;
        private final String categoryId;
        private final ImageBeanRemote.Filter filter;
        private final Double min;
        private final Double max;

        public ImageRequest(String searchText, String categoryId, ImageBeanRemote.Filter filter, Double min, Double max) {
            this.searchText = searchText;
            this.categoryId = categoryId;
            this.filter = filter;
            this.min = min;
            this.max = max;
        }

        public String getSearchText() {
            return searchText;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public ImageBeanRemote.Filter getFilter() {
            return filter;
        }

        public Double getMin() {
            return min;
        }

        public Double getMax() {
            return max;
        }
    }
}
