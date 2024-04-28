package br.com.wti.inventoryweb.managedbean;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Washington Antunes for wTI on 11/04/2023
 */
public abstract class BaseMB implements Serializable {

    private void add(final String key, final FacesMessage.Severity severity) {
        FacesContext fc = null;
        ResourceBundle bundle = null;
        String msg = null;
        try {
            fc = FacesContext.getCurrentInstance();
            bundle = fc.getApplication().getResourceBundle(fc, getMessage());
            msg = bundle.getString(key);
        } catch (MissingResourceException e) {
            msg = e.getKey();
        } catch (RuntimeException e) {
            msg = key;
        }
        fc.addMessage(null, new FacesMessage(severity, msg, msg));
    }

    protected String getMessage(){
        return "msg";
    }

    protected void messagemAlerta(String chave) {
        add(chave, FacesMessage.SEVERITY_WARN);
    }

    protected void messagemErro(String chave) {
        add(chave, FacesMessage.SEVERITY_ERROR);
    }

    protected void messagemSuccesso(String chave) {
        add(chave, FacesMessage.SEVERITY_INFO);
    }

    public boolean podeCriar() {
        return true;//TODO
    }

    public boolean podeProcurar() {
        return true;//TODO
    }

    public boolean podeAbrir() {
        return true;//TODO
    }

    public boolean podeAtualizar() {
        return true;//TODO
    }

    public boolean podeDesativar() {
        return true;//TODO
    }

    public String paginaRetorno() {
        return null;
    }
}
