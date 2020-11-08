package controller.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class Formatted extends SimpleTagSupport {
    private String firstName;
    private String lastName;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.print(formatted(firstName)+" "+formatted(lastName));
    }

    public String formatted(String text) {
        return text.substring(0,1).toUpperCase()+text.substring(1).toLowerCase();
    }
}
