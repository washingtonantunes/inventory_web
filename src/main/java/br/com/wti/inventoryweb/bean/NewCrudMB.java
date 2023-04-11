package br.com.wti.inventoryweb.bean;

import br.com.wti.inventoryweb.domain.model.RepositoryDataModel;
import br.com.wti.inventoryweb.repository.JpaSpecificationRepository;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.LazyDataModel;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author Washington Antunes for wTI on 11/04/2023
 */
public abstract class NewCrudMB<E extends Persistable<PK>, PK extends Serializable, R extends JpaSpecificationRepository<E, PK>> extends BaseMB {

  @Getter
  @Setter
  private E model;

  @Getter
  private LazyDataModel<E> searchResult;

  public NewCrudMB() {
  }

  public String pageReturn() {
    return null;
  }

  public String search() {

    this.searchResult = new RepositoryDataModel<E, PK>(getRepository(), getSpecification(), getSort());

    return pageReturn();
  }

  protected abstract R getRepository();

  protected Specification<E> getSpecification() {
    return null;
  };

  protected Sort getSort() {
    return Sort.unsorted();
  };
}
