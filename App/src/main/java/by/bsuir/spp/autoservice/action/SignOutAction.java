package by.bsuir.spp.autoservice.action;

import com.opensymphony.xwork2.Action;
import org.apache.struts2.ServletActionContext;

public class SignOutAction implements Action {
    @Override
    public String execute() throws Exception {
        ServletActionContext.getRequest().getSession().invalidate();
        return SUCCESS;
    }
}
