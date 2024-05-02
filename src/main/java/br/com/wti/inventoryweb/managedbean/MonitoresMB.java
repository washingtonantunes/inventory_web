package br.com.wti.inventoryweb.managedbean;

import br.com.wti.inventoryweb.domain.form.MonitorFiltroForm;
import br.com.wti.inventoryweb.domain.model.Monitor;
import br.com.wti.inventoryweb.service.MonitorService;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 * @author Washington Antunes for wTI on 26/04/2024
 */

@Getter
@Setter
@Component
@ViewScoped
public class MonitoresMB extends BaseMB {

    @Autowired
    private MonitorFiltroForm monitorFiltroForm;

    @Autowired
    private MonitorService monitorService;

    private LazyDataModel<Monitor> resultado;

    public String procurar() {
        if (!podeProcurar()) {
            messagemAlerta("pesquisar.negada");
            return paginaRetorno();
        }

        this.resultado = monitorService.procurarMonitores(getSpecification(), getSort());
        return paginaRetorno();
    }

    public String visualizar(long index) {
        if (!podeAbrir()) {
            messagemAlerta("visualizar.negada");
            return paginaRetorno();
        }

        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("monitorId", index);
        return "/page/monitor?faces-redirect=true";
    }

    public Specification<Monitor> getSpecification() {
        return monitorFiltroForm.toSpec();
    }

    protected Sort getSort() {
        return Sort.by(Sort.Direction.ASC, "numeroSerie");
    }

    public String acaoBotaoNovo() {
        return "/page/monitor.xhtml?faces-redirect=true";
    }
}
