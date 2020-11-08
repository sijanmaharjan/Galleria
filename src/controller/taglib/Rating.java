package controller.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class Rating extends SimpleTagSupport {
    private Float stars;
    private Integer count;

    public void setStars(Float stars) {
        this.stars = stars;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.print(getRating(stars));
        if(count != null){
            out.print(" ("+(Math.round(stars * 100.0) / 100.0)+" stars by "+count+" users)");
        }
    }
    public String getRating(Float stars){
        int star = (int) Math.floor(stars);
        StringBuilder ratings = new StringBuilder();
        for(int i=1; i<=star; i++){
            ratings.append("<i class='fas fa-star'></i>");
        }
        if(stars > Double.parseDouble(String.valueOf(star))){
            ratings.append("<i class='fas fa-star-half-alt'></i>");
            star += 2;
        } else {
            star += 1;
        }
        for(int i=star; i<=5; i++){
            ratings.append("<i class='far fa-star'></i>");
        }
        return ratings.toString();
    }
}
