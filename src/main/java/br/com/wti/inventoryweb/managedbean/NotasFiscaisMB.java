package br.com.wti.inventoryweb.managedbean;

import br.com.wti.inventoryweb.domain.form.NotaFiscalFiltroForm;
import br.com.wti.inventoryweb.domain.model.NotaFiscal;
import br.com.wti.inventoryweb.service.NotaFiscalService;
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
 * @author Washington Antunes for wTI on 01/05/2024
 */
@Getter
@Setter
@Component
@ViewScoped
public class NotasFiscaisMB extends BaseMB {

    @Autowired
    private NotaFiscalFiltroForm notaFiscalFiltroForm;

    @Autowired
    private NotaFiscalService notaFiscalService;

    private LazyDataModel<NotaFiscal> resultado;

    public String procurar() {
        if (!podeProcurar()) {
            messagemAlerta("pesquisar.negada");
            return paginaRetorno();
        }

        this.resultado = notaFiscalService.procurarNotasFiscais(getSpecification(), getSort());
        return paginaRetorno();
    }

    public String visualizar(long index) {
        if (!podeAbrir()) {
            messagemAlerta("visualizar.negada");
            return paginaRetorno();
        }

        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("notaFiscalId", index);
        return "/page/notaFiscal?faces-redirect=true";
    }

    public Specification<NotaFiscal> getSpecification() {
        return notaFiscalFiltroForm.toSpec();
    }

    protected Sort getSort() {
        return Sort.by(Sort.Direction.ASC, "numero");
    }

    public String acaoBotaoNovo() {
        return "/page/notaFiscal.xhtml?faces-redirect=true";
    }
}
