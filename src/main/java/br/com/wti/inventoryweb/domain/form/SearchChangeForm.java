package br.com.wti.inventoryweb.domain.form;

import br.com.wti.inventoryweb.domain.enums.TypeChangeEnum;
import br.com.wti.inventoryweb.domain.enums.TypeEntityEnum;
import br.com.wti.inventoryweb.domain.model.Change;
import com.google.common.collect.Lists;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;


/**
 * @author Washington Antunes for wTI on 11/04/2023
 */
@Getter
@Setter
@Component
public class SearchChangeForm {

  private LocalDate date;
  private String description;
  private TypeEntityEnum typeEntity;
  private List<TypeChangeEnum> typesEntityEnum = Lists.newArrayList();

  public Specification<Change> toSpec() {
    return (root, query, builder) -> {
      List<Predicate> predicates = Lists.newArrayList();
      if (ObjectUtils.isNotEmpty(date)) {
        Path<LocalDate> fieldDate = root.get("date");
        Predicate predicateDate = builder.equal(fieldDate, date);
        predicates.add(predicateDate);
      }
      if (StringUtils.isNotBlank(description)) {
        Path<String> fieldDescription = root.get("description");
        Predicate predicateDescription = builder.like(fieldDescription, "%" + description + "%");
        predicates.add(predicateDescription);
      }
      if (ObjectUtils.isNotEmpty(typeEntity)) {
        Path<TypeEntityEnum> fieldTypeEntity = root.get("typeEntity");
        Predicate predicateTypeEntity = builder.equal(fieldTypeEntity, typeEntity);
        predicates.add(predicateTypeEntity);
      }
      if (ObjectUtils.isNotEmpty(typesEntityEnum)) {
        Path<List<TypeChangeEnum>> fieldTypeChange = root.get("typeChange");
        Predicate predicateTypeChange = builder.in(fieldTypeChange).value(typesEntityEnum);
        predicates.add(predicateTypeChange);
      }
      return builder.and(predicates.toArray(new Predicate[0]));
    };
  }
}
