package br.com.wti.inventoryweb.bean;

import br.com.wti.inventoryweb.domain.enums.TypeChangeEnum;
import br.com.wti.inventoryweb.domain.enums.TypeEntityEnum;
import br.com.wti.inventoryweb.domain.form.FilterChangeForm;
import br.com.wti.inventoryweb.domain.model.Change;
import br.com.wti.inventoryweb.service.ChangeService;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.Getter;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @author Washington Antunes for wTI on 11/04/2023
 */


@Component
@RequestScope
public class ChangeListMB extends BaseMB {

  @Autowired
  private ChangeService changeService;

  @Getter
  @Autowired
  private FilterChangeForm filterChangeForm;

  @Getter
  private LazyDataModel<Change> searchResult;

  @Getter
  private List<TypeChangeEnum> listTypeChangeEnum;
  @Getter
  private List<TypeEntityEnum> listTypeEntityEnum;

  @PostConstruct
  public void init() {
    listTypeChangeEnum = TypeChangeEnum.typesEntity();
    listTypeEntityEnum = TypeEntityEnum.typesEntity();
  }

  public String search() {

    this.searchResult = changeService.findChangesByParams(getSpecification(), getSort());

    return null;
  }

  public Specification<Change> getSpecification() {
    return filterChangeForm.toSpec();
  }

  protected Sort getSort() {
    return Sort.by(Direction.DESC, "date");
  }
}
