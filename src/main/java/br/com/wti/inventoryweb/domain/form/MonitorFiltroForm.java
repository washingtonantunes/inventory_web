package br.com.wti.inventoryweb.domain.form;

import br.com.wti.inventoryweb.domain.model.Monitor;
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
 * @author Washington Antunes for wTI on 12/04/2023
 */
@Getter
@Setter
@Component
public class MonitorFiltroForm {

    private String parametro;

    public Specification<Monitor> toSpec() {
        return (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();

            if (StringUtils.isNotBlank(parametro)) {
                Path<String> fieldSerialNumber = root.get("numeroSerie");
                Predicate predicateSerialNumber = builder.like(fieldSerialNumber, "%" + parametro + "%");

                Path<String> fieldPatrimonyNumber = root.get("numeroPatrimonio");
                Predicate predicatePatrimonyNumber = builder.like(fieldPatrimonyNumber, "%" + parametro + "%");

                Predicate paramPredicate = builder.or(predicateSerialNumber, predicatePatrimonyNumber);
                predicates.add(paramPredicate);
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
