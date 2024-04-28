package br.com.wti.inventoryweb.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Washington Antunes for wTI on 27/04/2024
 */
@Getter
@Setter
@AllArgsConstructor
public class Historico {

    private LocalDateTime dataAlteracao;
    private String alteracao;
    private String usuario;
}
