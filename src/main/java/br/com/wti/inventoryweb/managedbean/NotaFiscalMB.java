package br.com.wti.inventoryweb.managedbean;

import br.com.wti.inventoryweb.domain.model.NotaFiscal;
import br.com.wti.inventoryweb.service.NotaFiscalService;
import com.google.common.collect.Lists;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Washington Antunes for wTI on 01/05/2024
 */
@Getter
@Slf4j
@Component
@ViewScoped
public class NotaFiscalMB extends BaseMB {

    private NotaFiscal notaFiscal;

    @Autowired
    private NotaFiscalService notaFiscalService;

    private List<String> empresaNotaFiscalLista;

    @PostConstruct
    public void init() {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        Object notaFiscalIdObjeto = flash.get("notaFiscalId");
        long notaFiscalId = 0;

        if (notaFiscalIdObjeto != null) {
            notaFiscalId = (Long) notaFiscalIdObjeto;
        }

        if (notaFiscalId > 0) {
            buscarNotaFiscal(notaFiscalId);
        } else {
            novoNotaFiscal();
        }

        iniciarListaAuxiliar();
    }

    private void buscarNotaFiscal(Long notaFiscalId) {
        notaFiscal = notaFiscalService.buscarNotaFiscal(notaFiscalId);
    }

    private void novoNotaFiscal() {
        notaFiscal = new NotaFiscal();
    }

    private void iniciarListaAuxiliar() {//TODO
        empresaNotaFiscalLista = Lists.newArrayList("Empresa 1", "Empresa 2");
    }
}
