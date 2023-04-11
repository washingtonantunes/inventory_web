package br.com.wti.inventoryweb.bean;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;

/**
 * @author Washington Antunes for wTI on 11/04/2023
 */
public abstract class BaseMB implements Serializable {

  private void add(String msg, FacesMessage.Severity severity) {
    FacesMessage facesMessage = new FacesMessage(msg);
    facesMessage.setSeverity(severity);

    FacesContext.getCurrentInstance().addMessage(null, facesMessage);
  }

  protected void messageInfo(String msg) {
    add(msg, FacesMessage.SEVERITY_INFO);
  }

  protected void messageWarn(String msg) {
    add(msg, FacesMessage.SEVERITY_WARN);
  }

  protected void messageError(String msg) {
    add(msg, FacesMessage.SEVERITY_WARN);
  }

  protected void messageSuccess() {
    messageInfo("sucess");
  }
}
