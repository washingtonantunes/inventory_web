package br.com.wti.inventoryweb.domain.model;

import br.com.wti.inventoryweb.listener.RevisaoListener;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import java.time.LocalDateTime;

/**
 * @author Washington Antunes for wTI on 04/05/2024
 */
@Getter
@Setter
@Entity
@ToString
@RevisionEntity(RevisaoListener.class)
public class Revisao extends DefaultRevisionEntity {

    private String usuario;
    private LocalDateTime dataHora;
}
