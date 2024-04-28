package br.com.wti.inventoryweb.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Washington Antunes for wTI on 26/04/2024
 */
@Getter
@RequiredArgsConstructor
public enum StatusEquipamentoEnum {

    DISPONIVEL("Disponível", "GREEN"),
    EM_USO("Em Uso", "ORANGE"),
    DESATIVADO("Desativado", "BLUE"),
    DESCARTADO("Descartado", "RED");

    private final String descricao;

    private final String cor;
}
