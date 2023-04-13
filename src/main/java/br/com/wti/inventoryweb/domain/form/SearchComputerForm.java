package br.com.wti.inventoryweb.domain.form;

import br.com.wti.inventoryweb.domain.enums.StatusEquipmentEnum;
import br.com.wti.inventoryweb.domain.enums.TypeChangeEnum;
import br.com.wti.inventoryweb.domain.enums.TypeEntityEnum;
import br.com.wti.inventoryweb.domain.model.Change;
import br.com.wti.inventoryweb.domain.model.Computer;
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
 * @author Washington Antunes for wTI on 12/04/2023
 */
@Getter
@Setter
@Component
public class SearchComputerForm {

  private String param;

  public Specification<Computer> toSpec() { //Não será buscando com o status DISABLED e DISCARDED
    return (root, query, builder) -> {
      List<Predicate> predicates = Lists.newArrayList();

      Predicate notEqualPredicateDISABLED = builder.notEqual(root.get("status"), StatusEquipmentEnum.DISABLED);
      Predicate notEqualPredicateDISCARDED = builder.notEqual(root.get("status"), StatusEquipmentEnum.DISCARDED);

      Predicate statusPredicate = builder.and(notEqualPredicateDISABLED, notEqualPredicateDISCARDED);

      if (StringUtils.isNotBlank(param)) {
        Path<String> fieldSerialNumber = root.get("serialNumber");
        Predicate predicateSerialNumber = builder.like(fieldSerialNumber, "%" + param + "%");
        predicates.add(predicateSerialNumber);

        Path<String> fieldPatrimonyNumber = root.get("patrimonyNumber");
        Predicate predicatePatrimonyNumber = builder.like(fieldPatrimonyNumber, "%" + param + "%");
        predicates.add(predicatePatrimonyNumber);

        Predicate paramPredicate = builder.or(predicateSerialNumber, predicatePatrimonyNumber);

        return builder.and(statusPredicate, paramPredicate);
      }
      return statusPredicate;
    };
  }
}
