package br.com.wti.inventoryweb.domain.form;

import br.com.wti.inventoryweb.domain.model.Change;
import br.com.wti.inventoryweb.domain.model.Invoice;
import com.google.common.collect.Lists;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 * @author Washington Antunes for wTI on 19/04/2023
 */
@Getter
@Setter
@Component
public class FilterInvoiceForm {

  private String number;

  public Specification<Invoice> toSpec() {
    return (root, query, builder) -> {
      List<Predicate> predicates = Lists.newArrayList();

      if (StringUtils.isNotBlank(number)) {
        Path<String> fieldNumber = root.get("number");
        Predicate predicateNumber = builder.like(fieldNumber, "%" + number + "%");
        predicates.add(predicateNumber);
      }
      return builder.and(predicates.toArray(new Predicate[0]));
    };
  }
}
