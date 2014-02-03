/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.jee.travel_company.view;


/**
 *
 * @author mist
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@ManagedBean(name = "login")
@RequestScoped
public class LoginBean {

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    public boolean isLoggedCustomer() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("customer");
    }

    public boolean isLoggedRoot() {
        boolean ret = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("root");
        return ret;
    }

    public boolean isLoggedGuest() {
        boolean ret = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("guest");
        return ret;
    }

    public String getLoginName() {
        Principal p = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        if (p == null) {
            return "guest";
        }
        return p.getName();
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "index.xhtml";
    }
}
