package br.com.wti.inventoryweb.domain.dto;

import br.com.wti.inventoryweb.domain.enums.TipoRevisaoEnum;
import br.com.wti.inventoryweb.domain.model.Revisao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Washington Antunes for wTI on 04/05/2024
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class EntidadeComRevisao<T> {

    private Revisao revisao;
    private TipoRevisaoEnum tipo;
    private T entidade;
}
