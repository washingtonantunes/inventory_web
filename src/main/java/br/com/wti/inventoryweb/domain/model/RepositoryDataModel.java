package br.com.wti.inventoryweb.domain.model;

import br.com.wti.inventoryweb.repository.JpaSpecificationRepository;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author Washington Antunes for wTI on 11/04/2023
 */
public class RepositoryDataModel<E extends Persistable<PK>, PK extends Serializable> extends LazyDataModel<E> {

  private JpaSpecificationRepository<E, PK> repository;

  private Specification<E> specification;

  private Sort sort;

  private Page<E> data;

  public RepositoryDataModel(final JpaSpecificationRepository<E, PK> repository, final Specification<E> predicate, final Sort sort) {
    this.repository = repository;
    this.specification = specification;
    this.sort = sort;
  }

  @Override
  public int count(Map<String, FilterMeta> filterBy) {
    return 0;
  }

  public List<E> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {

    data = repository.findAll(specification, createPageable(getPageNumber(first, pageSize), pageSize, this.sort));

    setRowCount((int) this.data.getTotalElements());

    return this.data.getContent();
  }

  private int getPageNumber(int first, int pageSize) {
    if (first < pageSize) {
      return 0;
    }

    return first / pageSize;
  }

  @Override
  public E getRowData(String rowKey) {
    for (E model : this.data) {
      if (model.getId().toString().equals(rowKey)) {
        return model;
      }
    }
    return null;
  }

  @Override
  public String getRowKey(E object) {
    return object.getId().toString();
  }

  private Pageable createPageable(final int page, final int pageSize, final Sort sort) {
    return PageRequest.of(page, pageSize, sort);
  }
}
