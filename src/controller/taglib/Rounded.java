package controller.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class Rounded extends SimpleTagSupport {

    private Double number;

    public void setNumber(Double number) {
        this.number = number;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.print(Math.round(number * 100.0) / 100.0);
    }
}
