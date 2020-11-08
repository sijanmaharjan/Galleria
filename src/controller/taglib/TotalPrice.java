package controller.taglib;

import bean.view.Wish;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.List;

public class TotalPrice extends SimpleTagSupport {

    private List<Wish> wishes;

    public void setWishes(List<Wish> wishes) {
        this.wishes = wishes;
    }
}
