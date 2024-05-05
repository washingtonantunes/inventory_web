package br.com.wti.inventoryweb.listener;

import br.com.wti.inventoryweb.domain.model.Revisao;
import org.hibernate.envers.RevisionListener;

import java.time.LocalDateTime;

/**
 * @author Washington Antunes for wTI on 04/05/2024
 */
public class RevisaoListener implements RevisionListener {
    @Override
    public void newRevision(Object o) {
        Revisao revisao = (Revisao) o;
        revisao.setUsuario("Admin");//TODO
        revisao.setDataHora(LocalDateTime.now());
    }
}
