package br.com.wti.inventoryweb.domain.form;

import br.com.wti.inventoryweb.domain.model.NotaFiscal;
import com.google.common.collect.Lists;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Washington Antunes for wTI on 19/04/2023
 */
@Getter
@Setter
@Component
public class NotaFiscalFiltroForm {

    private String parametro;

    public Specification<NotaFiscal> toSpec() {
        return (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();

            if (StringUtils.isNotBlank(parametro)) {
                Path<String> fieldNumber = root.get("numero");
                Predicate predicateNumber = builder.like(fieldNumber, "%" + parametro + "%");
                predicates.add(predicateNumber);
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
