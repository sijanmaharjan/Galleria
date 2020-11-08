package controller.taglib;

import bean.view.TransactionStatement;
import util.DateMapper;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.ZoneId;

public class Statement extends SimpleTagSupport {

    private TransactionStatement statement;
    private ZoneId zoneId;

    public void setObj(TransactionStatement obj) {
        this.statement = obj;
    }

    public void setZone(ZoneId zone) {
        this.zoneId = zone;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.print("<tr>");
        out.print("<td>"+ statement.getDate().toInstant().atZone(zoneId).toLocalDateTime().toString() +"</td>");
        out.print("<td "+(statement.getDebit()==null && statement.getCredit()==null?"colspan=3 style='text-align:center'":"")+">"+
                (statement.getDebit()==null && statement.getCredit()==null?"<small><em>"+statement.getParticulars()+"</em></small>":statement.getParticulars())
                +"</td>");
        if(statement.getDebit()!=null || statement.getCredit()!=null) {
            out.print("<td>" + (statement.getDebit() != null ? "$" + statement.getDebit() : "") + "</td>");
            out.print("<td>" + (statement.getCredit() != null ? "$" + statement.getCredit() : "") + "</td>");
        }
        out.print("</tr>");
    }
}
