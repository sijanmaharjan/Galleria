package controller.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Age extends SimpleTagSupport {
    private Date dob;

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.print(getAge(dob));
    }

    public String getAge(Date dob){
        LocalDate date = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period p = Period.between(date, LocalDate.now());
        return (p.getYears()+"yo");
    }
}
