package br.com.wti.inventoryweb.service;

import br.com.wti.inventoryweb.domain.model.Invoice;
import br.com.wti.inventoryweb.domain.model.RepositoryDataModel;
import br.com.wti.inventoryweb.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author Washington Antunes for wTI on 16/04/2023
 */
@Service
public class InvoiceService {

  @Autowired
  private InvoiceRepository invoiceRepository;

  public RepositoryDataModel<Invoice, Long> findAllInvoice(final Specification<Invoice> specification, final Sort sort) {
    return new RepositoryDataModel<Invoice, Long>(invoiceRepository, specification, sort);
  }
}
