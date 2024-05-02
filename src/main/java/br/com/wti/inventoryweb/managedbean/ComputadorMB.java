package br.com.wti.inventoryweb.managedbean;

import br.com.wti.inventoryweb.domain.enums.LocalizacaoEnum;
import br.com.wti.inventoryweb.domain.enums.StatusEquipamentoEnum;
import br.com.wti.inventoryweb.domain.enums.TipoComputadorEnum;
import br.com.wti.inventoryweb.domain.model.Computador;
import br.com.wti.inventoryweb.domain.model.Historico;
import br.com.wti.inventoryweb.domain.model.NotaFiscal;
import br.com.wti.inventoryweb.exception.NegocioException;
import br.com.wti.inventoryweb.service.ComputadorService;
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
public class ComputadorMB extends BaseMB {

    private Computador computador;

    private List<TipoComputadorEnum> tipoComputadorLista;
    private List<String> modeloComputadorLista;
    private List<String> fabricanteComputadorLista;
    private List<String> memoriaRamComputadorLista;
    private List<String> discoRigidoComputadorLista;
    private List<NotaFiscal> notaFiscalLista;

    @Autowired
    private ComputadorService computadorService;

    @Autowired
    private NotaFiscalService notaFiscalService;

    @PostConstruct
    public void init() {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        Object computadorIdObjeto = flash.get("computadorId");
        long computadorId = 0;

        if (computadorIdObjeto != null) {
            computadorId = (Long) computadorIdObjeto;
        }

        if (computadorId > 0) {
            buscarComputador(computadorId);
        } else {
            novoComputador();
        }

        iniciarListaAuxiliar();
    }

    private void buscarComputador(Long computadorId) {
        computador = computadorService.buscarComputador(computadorId);
    }

    private void novoComputador() {
        computador = new Computador();
    }

    private void iniciarListaAuxiliar() {//TODO
        tipoComputadorLista = Lists.newArrayList(TipoComputadorEnum.values());
        modeloComputadorLista = Lists.newArrayList("HP 240 G7 Notebook PC", "HP 240 G6 Notebook PC");
        fabricanteComputadorLista = Lists.newArrayList("HP", "LG", "Intel");
        memoriaRamComputadorLista = Lists.newArrayList("4 GB", "8 GB", "16 GB");
        discoRigidoComputadorLista =  Lists.newArrayList("250 GB", "350GB", "500GB", "1 TB");
        notaFiscalLista = notaFiscalService.buscarNotasFiscaisParaComboBox();
    }

    public String salvar() {
        if (!podeCriar()) {
            messagemAlerta("criar.negada");
            return paginaRetorno();
        }

        computador.setDataEntrada(LocalDateTime.now());
        computador.setStatus(StatusEquipamentoEnum.DISPONIVEL);
        computador.setLocalizacao(LocalizacaoEnum.TECNOLOGIA);
        computador.setHistoricos(Lists.newArrayList(new Historico(LocalDateTime.now(), "Computador Criado", "Usuario 1")));//TODO

        try {
            computadorService.salvarComputador(computador);
            messagemSuccesso("computador.salvo");
        } catch (ConstraintViolationException ex) {
            computador.setId(null);
            for (ConstraintViolation<?> exp : ex.getConstraintViolations()) {
                messagemErro(exp.getMessage());
            }
        } catch (DataIntegrityViolationException e) {
            computador.setId(null);
            log.error("Ocorreu um erro ao persistir a entidade", e);
            messagemErro("registro.duplicado");
        } catch (Exception e) {
            computador.setId(null);
            log.error("Ocorreu um erro ao persistir a entidade", e);
            messagemErro("computador.erro.salvar");
        }

        return paginaRetorno();
    }

    public String atualizar() {
        if (!podeAtualizar()) {
            messagemAlerta("atualizar.negada");
            return paginaRetorno();
        }

        computador.setHistoricos(Lists.newArrayList(new Historico(LocalDateTime.now(), "Computador Atualizado", "Usuario 1")));//TODO

        try {
            computador = computadorService.salvarComputador(computador);
            messagemSuccesso("computador.atualizado");
        } catch (ConstraintViolationException ex) {
            for (ConstraintViolation<?> exp : ex.getConstraintViolations()) {
                messagemErro(exp.getMessage());
            }
        } catch (DataIntegrityViolationException e) {
            log.error("Ocorreu um erro ao atualizar o computador", e);
            messagemErro("registro.duplicado");
        } catch (Exception e) {
            log.error("Ocorreu um erro ao atualizar o computador", e);
            messagemErro("computador.erro.atualizar");
        }

        return paginaRetorno();
    }

    public String desativar() {
        if (!podeDesativar()) {
            messagemAlerta("desativar.negada");
            return paginaRetorno();
        }

        computador.setHistoricos(Lists.newArrayList(new Historico(LocalDateTime.now(), "Computador Atualizado", "Usuario 1")));//TODO

        try {
            computador = computadorService.desativarComputador(computador);
            messagemSuccesso("computador.desativado");
        } catch (NegocioException e) {
            messagemErro(e.getMessage());
        } catch (Exception e) {
            log.error("Ocorreu um erro ao desativar o computador", e);
            messagemErro("computador.erro.desativar");
        }

        return paginaRetorno();
    }

    public void onTabChange(TabChangeEvent event) {
        if (computador.getHistoricos() == null && event != null && event.getTab().getTitle().equals("Histórico")) {
            List<Historico> historicoList = Lists.newArrayList();

            historicoList.add(new Historico(LocalDateTime.of(2023, 6, 2, 20, 5), "Alteração 1", "Usuario 1"));
            historicoList.add(new Historico(LocalDateTime.of(2023, 7, 3, 20, 5), "Alteração 2", "Usuario 1"));

            computador.setHistoricos(historicoList);
        }
    }
}
