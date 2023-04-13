package br.com.wti.inventoryweb.bean;

import br.com.wti.inventoryweb.domain.enums.TypeChangeEnum;
import br.com.wti.inventoryweb.domain.enums.TypeEntityEnum;
import br.com.wti.inventoryweb.domain.form.SearchChangeForm;
import br.com.wti.inventoryweb.domain.model.Change;
import br.com.wti.inventoryweb.repository.ChangeRepository;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 * @author Washington Antunes for wTI on 11/04/2023
 */
@Getter
@Setter
@Component
@RequestScoped
public class ChangeMB extends NewCrudMB<Change, Long, ChangeRepository> {

  @Autowired
  private ChangeRepository repository;
  @Autowired
  private SearchChangeForm searchChangeForm;

  private List<TypeChangeEnum> listTypeChangeEnum;
  private List<TypeEntityEnum> listTypeEntityEnum;

  @PostConstruct
  public void init() {
    listTypeChangeEnum = TypeChangeEnum.typesEntity();
    listTypeEntityEnum = TypeEntityEnum.typesEntity();
  }

  @Override
  public Specification<Change> getSpecification() {
    return searchChangeForm.toSpec();
  }

  @Override
  protected Sort getSort() {
    return Sort.by(Direction.DESC, "date");
  }
}
