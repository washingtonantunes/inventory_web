package br.com.wti.inventoryweb.bean;

import br.com.wti.inventoryweb.domain.form.FilterComputerForm;
import br.com.wti.inventoryweb.domain.model.Computer;
import br.com.wti.inventoryweb.service.ComputerService;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 * @author Washington Antunes for wTI on 12/04/2023
 */
@Component
@ViewScoped
public class ComputerListMB extends BaseMB {

  @Autowired
  private ComputerService computerService;
  @Getter
  @Autowired
  private FilterComputerForm filterComputerForm;

  @Getter
  private LazyDataModel<Computer> searchResult;

  public String search() {

    this.searchResult = computerService.findComputersByParams(getSpecification(), getSort());

    return null;
  }

  public String view(long index) {
    Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    flash.put("computerId", index);
    return "/page/computerDetail?faces-redirect=true";
  }

  public Specification<Computer> getSpecification() {
    return filterComputerForm.toSpec();
  }

  protected Sort getSort() {
    return Sort.by(Direction.ASC, "serialNumber");
  }
}
