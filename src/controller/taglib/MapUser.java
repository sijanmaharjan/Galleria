package controller.taglib;

import bean.remote.UserBeanRemote;
import bean.view.DetailedUser;

import javax.ejb.EJB;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class MapUser extends SimpleTagSupport {
    private String UID;

    @EJB
    private UserBeanRemote userBeanRemote;
    private Formatted formatted;
    private Rating rating;
    private Age age;

    public void setUID(String UID) {
        this.UID = UID;
        this.formatted = new Formatted();
        this.rating = new Rating();
        this.age = new Age();
    }

    @Override
    public void doTag() throws JspException, IOException {
        DetailedUser user = userBeanRemote.getDetails(UID);
        JspWriter out = getJspContext().getOut();
        out.print("{" +
                "id:'" +user.getId()+"'," +
                "name:'" +formatted.formatted(user.getFirstName())+" "+formatted.formatted(user.getLastName())+"'," +
                "username:'@" +user.getUsername()+"'," +
                "ratings:'" +rating.getRating(user.getOverallRating()).replace("'","\\'")+"'," +
                "account:'" +user.getAccountNumber()+"'," +
                "email:'" +user.getEmail()+"'," +
                "dob:'" +age.getAge(user.getBirthDate())+"'," +
                "email:'" +user.getEmail()+"'," +
                "gender:'" +user.getGender()+"'," +
                "uploads:'" +user.getTotalUploads()+"'," +
                "likesCollected:'" +user.getLikesCollected()+"'," +
                "earnings:'" +user.getEarned()+"'" +
                "}");
    }
}
