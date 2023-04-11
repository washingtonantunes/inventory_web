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
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;


/**
 * @author Washington Antunes for wTI on 11/04/2023
 */
@Getter
public class SearchChangeForm {

  private LocalDate date;
  private String description;
  private String typeEntity;
  private String typeChange;

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
      if (StringUtils.isNotBlank(typeEntity)) {
        TypeEntityEnum typeEntityEnum = TypeEntityEnum.valueOf(typeEntity);
        Path<TypeEntityEnum> fieldTypeEntity = root.get("typeEntity");
        Predicate predicateTypeEntity = builder.equal(fieldTypeEntity, typeEntityEnum);
        predicates.add(predicateTypeEntity);
      }
      if (StringUtils.isNotBlank(typeChange)) {
        TypeChangeEnum typeChangeEnum = TypeChangeEnum.valueOf(typeChange);
        Path<TypeChangeEnum> fieldTypeChange = root.get("typeChange");
        Predicate predicateTypeChange = builder.equal(fieldTypeChange, typeChangeEnum);
        predicates.add(predicateTypeChange);
      }
      return builder.and(predicates.toArray(new Predicate[0]));
    };
  }
}
