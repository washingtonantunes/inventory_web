package br.com.wti.inventoryweb.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.RevisionType;

/**
 * @author Washington Antunes for wTI on 04/05/2024
 */
@Getter
@RequiredArgsConstructor
public enum TipoRevisaoEnum {

    INCLUSAO("Inclusão"),
    ALTERACAO("Alteração"),
    EXCLUSAO("Exclusão");

    private final String descricao;

    public static TipoRevisaoEnum buscarPorTipo(RevisionType tipo) {
        switch (tipo) {
            case ADD:
                return INCLUSAO;
            case MOD:
                return ALTERACAO;
            case DEL:
                return EXCLUSAO;
            default:
                throw new IllegalArgumentException("Tipo desconhecido: " + tipo);
        }
    }
}
