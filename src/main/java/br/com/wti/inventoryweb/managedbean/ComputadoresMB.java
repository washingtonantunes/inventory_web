package br.com.wti.inventoryweb.managedbean;

import br.com.wti.inventoryweb.domain.form.ComputadorFiltroForm;
import br.com.wti.inventoryweb.domain.model.Computador;
import br.com.wti.inventoryweb.service.ComputadorService;
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
public class ComputadoresMB extends BaseMB {

    @Autowired
    private ComputadorFiltroForm computadorFiltroForm;

    @Autowired
    private ComputadorService computadorService;

    private LazyDataModel<Computador> resultado;

    public String procurar() {
        if (!podeProcurar()) {
            messagemAlerta("pesquisar.negada");
            return paginaRetorno();
        }

        this.resultado = computadorService.procurarComputadores(getSpecification(), getSort());
        return paginaRetorno();
    }

    public String visualizar(long index) {
        if (!podeAbrir()) {
            messagemAlerta("visualizar.negada");
            return paginaRetorno();
        }

        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("computadorId", index);
        return "/page/computador?faces-redirect=true";
    }

    public Specification<Computador> getSpecification() {
        return computadorFiltroForm.toSpec();
    }

    protected Sort getSort() {
        return Sort.by(Sort.Direction.ASC, "numeroSerie");
    }

    public String acaoBotaoNovo() {
        return "/page/computador.xhtml?faces-redirect=true";
    }
}
