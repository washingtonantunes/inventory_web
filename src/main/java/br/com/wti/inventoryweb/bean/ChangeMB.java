package br.com.wti.inventoryweb.bean;

import br.com.wti.inventoryweb.domain.enums.TypeChangeEnum;
import br.com.wti.inventoryweb.domain.enums.TypeEntityEnum;
import br.com.wti.inventoryweb.domain.form.SearchChangeForm;
import br.com.wti.inventoryweb.domain.model.Change;
import br.com.wti.inventoryweb.repository.ChangeRepository;
import br.com.wti.inventoryweb.service.ChangeService;
import com.google.common.collect.Lists;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @author Washington Antunes for wTI on 11/04/2023
 */
@Getter
@Setter
@Component
@RequestScope
public class ChangeMB extends NewCrudMB<Change, Integer, ChangeRepository> {

  @Autowired
  private ChangeRepository repository;

  private TypeEntityEnum selectedTypeEntity;
  private List<TypeChangeEnum> selectedTypesChange;
  private LocalDate selectedDate;
  private String description;

  private List<TypeChangeEnum> listTypeChangeEnum;

  private List<TypeEntityEnum> listTypeEntityEnum;

  @PostConstruct
  public void init() {
    listTypeChangeEnum = TypeChangeEnum.typesEntity();
    listTypeEntityEnum = TypeEntityEnum.typesEntity();
  }

  @Override
  public Specification<Change> getSpecification() {
    return (root, query, builder) -> {
      List<Predicate> predicates = Lists.newArrayList();
      if (ObjectUtils.isNotEmpty(selectedDate)) {
        Path<LocalDate> fieldDate = root.get("date");
        Predicate predicateDate = builder.equal(fieldDate, selectedDate);
        predicates.add(predicateDate);
      }
      if (StringUtils.isNotBlank(description)) {
        Path<String> fieldDescription = root.get("description");
        Predicate predicateDescription = builder.like(fieldDescription, "%" + description + "%");
        predicates.add(predicateDescription);
      }
      if (ObjectUtils.isNotEmpty(selectedTypeEntity)) {
        Path<TypeEntityEnum> fieldTypeEntity = root.get("typeEntity");
        Predicate predicateTypeEntity = builder.equal(fieldTypeEntity, selectedTypeEntity);
        predicates.add(predicateTypeEntity);
      }
      if (ObjectUtils.isNotEmpty(selectedTypesChange)) {
        Path<List<TypeChangeEnum>> fieldTypeChange = root.get("typeChange");
        Predicate predicateTypeChange = builder.in(fieldTypeChange).value(selectedTypesChange);
        predicates.add(predicateTypeChange);
      }
      return builder.and(predicates.toArray(new Predicate[0]));
    };
  }

  @Override
  protected Sort getSort() {
    return Sort.by(Direction.DESC, "date");
  }
}
