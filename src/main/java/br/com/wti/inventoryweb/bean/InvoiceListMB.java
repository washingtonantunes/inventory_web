package br.com.wti.inventoryweb.bean;

import br.com.wti.inventoryweb.domain.model.Invoice;
import br.com.wti.inventoryweb.service.InvoiceService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 * @author Washington Antunes for wTI on 16/04/2023
 */
@Component
@ViewScoped
public class InvoiceListMB extends BaseMB {

  @Autowired
  private InvoiceService invoiceService;

  @Getter
  private LazyDataModel<Invoice> searchResult;

  @PostConstruct
  public void init() {
    this.searchResult = invoiceService.findAllInvoice(getSpecification(), getSort());
  }

  public Specification<Invoice> getSpecification() {
    return null;
  }

  protected Sort getSort() {
    return Sort.by(Direction.DESC, "dateEntry");
  }
}
