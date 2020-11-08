package controller.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class IfSelected extends SimpleTagSupport {
    private String id;
    private String selected;

    public void setId(String id) {
        this.id = id;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if(id.equals(selected)) {
            StringWriter sw = new StringWriter();
            getJspBody().invoke(sw);
            JspWriter out = getJspContext().getOut();
            out.print(sw.toString());
        }
    }
}
