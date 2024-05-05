package br.com.wti.inventoryweb.managedbean;

import br.com.wti.inventoryweb.domain.dto.EntidadeComRevisao;
import br.com.wti.inventoryweb.domain.enums.LocalizacaoEnum;
import br.com.wti.inventoryweb.domain.enums.StatusEquipamentoEnum;
import br.com.wti.inventoryweb.domain.model.Monitor;
import br.com.wti.inventoryweb.domain.model.NotaFiscal;
import br.com.wti.inventoryweb.exception.NegocioException;
import br.com.wti.inventoryweb.service.MonitorService;
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
 * @author Washington Antunes for wTI on 26/04/2024
 */

@Getter
@Slf4j
@Component
@ViewScoped
public class MonitorMB extends BaseMB {

    private Monitor monitor;

    private List<EntidadeComRevisao<Monitor>> auditoria;

    private List<String> modeloMonitorLista;
    private List<String> fabricanteMonitorLista;
    private List<NotaFiscal> notaFiscalLista;

    @Autowired
    private MonitorService monitorService;

    @Autowired
    private NotaFiscalService notaFiscalService;

    @PostConstruct
    public void init() {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        Object monitor = flash.get("monitor");

        if (monitor != null) {
            this.monitor = (Monitor) monitor;
        } else {
            novoMonitor();
        }

        iniciarListaAuxiliar();
    }

    private void novoMonitor() {
        monitor = new Monitor();
    }

    private void iniciarListaAuxiliar() {//TODO
        modeloMonitorLista = Lists.newArrayList("Modelo 1", "Modelo 2");
        fabricanteMonitorLista = Lists.newArrayList("HP", "LG");
        notaFiscalLista = notaFiscalService.buscarNotasFiscaisParaComboBox();
    }

    public String salvar() {
        if (!podeCriar()) {
            messagemAlerta("criar.negada");
            return paginaRetorno();
        }

        monitor.setDataEntrada(LocalDateTime.now());
        monitor.setStatus(StatusEquipamentoEnum.DISPONIVEL);
        monitor.setLocalizacao(LocalizacaoEnum.TECNOLOGIA);

        try {
            monitorService.salvarMonitor(monitor);
            messagemSuccesso("monitor.salvo");
        } catch (ConstraintViolationException ex) {
            monitor.setId(null);
            for (ConstraintViolation<?> exp : ex.getConstraintViolations()) {
                messagemErro(exp.getMessage());
            }
        } catch (DataIntegrityViolationException e) {
            monitor.setId(null);
            log.error("Ocorreu um erro ao persistir a entidade", e);
            messagemErro("registro.duplicado");
        } catch (Exception e) {
            monitor.setId(null);
            log.error("Ocorreu um erro ao persistir a entidade", e);
            messagemErro("monitor.erro.salvar");
        }

        return paginaRetorno();
    }

    public String atualizar() {
        if (!podeAtualizar()) {
            messagemAlerta("atualizar.negada");
            return paginaRetorno();
        }

        try {
            monitor = monitorService.salvarMonitor(monitor);
            messagemSuccesso("monitor.atualizado");
        } catch (ConstraintViolationException ex) {
            for (ConstraintViolation<?> exp : ex.getConstraintViolations()) {
                messagemErro(exp.getMessage());
            }
        } catch (DataIntegrityViolationException e) {
            log.error("Ocorreu um erro ao atualizar o monitor", e);
            messagemErro("registro.duplicado");
        } catch (Exception e) {
            log.error("Ocorreu um erro ao atualizar o monitor", e);
            messagemErro("monitor.erro.atualizar");
        }

        return paginaRetorno();
    }

    public String desativar() {
        if (!podeDesativar()) {
            messagemAlerta("desativar.negada");
            return paginaRetorno();
        }

        try {
            monitor = monitorService.desativarMonitor(monitor);
            messagemSuccesso("monitor.desativado");
        } catch (NegocioException e) {
            messagemErro(e.getMessage());
        } catch (Exception e) {
            log.error("Ocorreu um erro ao desativar o monitor", e);
            messagemErro("monitor.erro.desativar");
        }

        return paginaRetorno();
    }

    public void onTabChange(TabChangeEvent event) {
        if(event != null && event.getTab().getTitle().equals("Histórico")) {
            auditoria = monitorService.buscarAuditoria(monitor.getId());
        }
    }
}
