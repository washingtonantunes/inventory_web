package br.com.wti.inventoryweb.managedbean;

import br.com.wti.inventoryweb.domain.dto.EntidadeComRevisao;
import br.com.wti.inventoryweb.domain.model.NotaFiscal;
import br.com.wti.inventoryweb.service.NotaFiscalService;
import com.google.common.collect.Lists;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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

    private List<EntidadeComRevisao<NotaFiscal>> auditoria;

    @Autowired
    private NotaFiscalService notaFiscalService;

    private List<String> empresaNotaFiscalLista;

    @PostConstruct
    public void init() {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        Object notaFiscal = flash.get("notaFiscal");

        if (notaFiscal != null) {
            this.notaFiscal = (NotaFiscal) notaFiscal;
        } else {
            novoNotaFiscal();
        }

        iniciarListaAuxiliar();
    }

    private void novoNotaFiscal() {
        notaFiscal = new NotaFiscal();
    }

    private void iniciarListaAuxiliar() {//TODO
        empresaNotaFiscalLista = Lists.newArrayList("Empresa 1", "Empresa 2");
    }

    public String salvar() {
        if (!podeCriar()) {
            messagemAlerta("criar.negada");
            return paginaRetorno();
        }

        notaFiscal.setDataEntrada(LocalDateTime.now());

        try {
            notaFiscalService.salvarNotaFiscal(notaFiscal);
            messagemSuccesso("nota.fiscal.salvo");
        } catch (ConstraintViolationException ex) {
            notaFiscal.setId(null);
            for (ConstraintViolation<?> exp : ex.getConstraintViolations()) {
                messagemErro(exp.getMessage());
            }
        } catch (DataIntegrityViolationException e) {
            notaFiscal.setId(null);
            log.error("Ocorreu um erro ao persistir a entidade", e);
            messagemErro("registro.duplicado");
        } catch (Exception e) {
            notaFiscal.setId(null);
            log.error("Ocorreu um erro ao persistir a entidade", e);
            messagemErro("nota.fiscal.erro.salvar");
        }

        return paginaRetorno();
    }

    public String atualizar() {
        if (!podeAtualizar()) {
            messagemAlerta("atualizar.negada");
            return paginaRetorno();
        }

        try {
            notaFiscal = notaFiscalService.salvarNotaFiscal(notaFiscal);
            messagemSuccesso("nota.fiscal.atualizado");
        } catch (ConstraintViolationException ex) {
            for (ConstraintViolation<?> exp : ex.getConstraintViolations()) {
                messagemErro(exp.getMessage());
            }
        } catch (DataIntegrityViolationException e) {
            log.error("Ocorreu um erro ao atualizar a nota fiscal", e);
            messagemErro("registro.duplicado");
        } catch (Exception e) {
            log.error("Ocorreu um erro ao atualizar a nota fiscal", e);
            messagemErro("nota.fiscal.erro.atualizar");
        }

        return paginaRetorno();
    }

    public void onTabChange(TabChangeEvent event) {
        if(event != null && event.getTab().getTitle().equals("Histórico")) {
            auditoria = notaFiscalService.buscarAuditoria(notaFiscal.getId());
        }
    }
}
