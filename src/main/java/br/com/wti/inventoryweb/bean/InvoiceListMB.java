package br.com.wti.inventoryweb.bean;

import br.com.wti.inventoryweb.domain.form.FilterInvoiceForm;
import br.com.wti.inventoryweb.domain.model.Invoice;
import br.com.wti.inventoryweb.service.InvoiceService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
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
  @Setter
  private Invoice selectedInvoice;

  @Getter
  private LazyDataModel<Invoice> searchResult;

  @Getter
  @Autowired
  private FilterInvoiceForm filterInvoiceForm;

  @PostConstruct
  public void init() {
    this.searchResult = invoiceService.findAllInvoice(getSpecification(), getSort());
  }

  public Specification<Invoice> getSpecification() {
    return filterInvoiceForm.toSpec();
  }

  protected Sort getSort() {
    return Sort.by(Direction.DESC, "dateEntry");
  }
}
