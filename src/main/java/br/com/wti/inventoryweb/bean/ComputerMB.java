package br.com.wti.inventoryweb.bean;

import br.com.wti.inventoryweb.domain.form.SearchComputerForm;
import br.com.wti.inventoryweb.domain.model.Computer;
import br.com.wti.inventoryweb.repository.ComputerRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @author Washington Antunes for wTI on 12/04/2023
 */
@Getter
@Setter
@Component
@RequestScope
public class ComputerMB extends NewCrudMB<Computer, Long, ComputerRepository> {

  @Autowired
  private ComputerRepository repository;
  @Autowired
  private SearchComputerForm searchComputerForm;

  @Override
  public Specification<Computer> getSpecification() {
    return searchComputerForm.toSpec();
  }

  @Override
  protected Sort getSort() {
    return Sort.by(Direction.ASC, "serialNumber");
  }
}
