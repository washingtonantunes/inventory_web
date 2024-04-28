package br.com.wti.inventoryweb.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Washington Antunes for wTI on 26/04/2024
 */
@Getter
@RequiredArgsConstructor
public enum LocalizacaoEnum {

    TECNOLOGIA("Tecnologia"),
    OPERACAO("Operação"),
    USER("Usuário");

    private final String descricao;
}
