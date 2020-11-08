package controller.taglib;

import bean.entity.User;
import bean.remote.UserBeanRemote;

import javax.ejb.EJB;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class Uploader extends SimpleTagSupport {
    private String UID;
    private String current;

    @EJB
    private UserBeanRemote userBeanRemote;

    public void setUID(String UID) {
        this.UID = UID;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        if(UID.equals(current)){
            out.print("You");
        }else {
            User user = userBeanRemote.find(UID);
            if (user == null) {
                out.print("unknown");
            } else {
                out.print(formatted(user.getFirstName()) + " " + formatted(user.getLastName()));
            }
        }
    }

    private String formatted(String text) {
        return text.substring(0,1).toUpperCase()+text.substring(1).toLowerCase();
    }
}
