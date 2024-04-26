package br.com.wti.inventoryweb.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * @author Washington Antunes for wTI on 16/04/2023
 */
@Getter
@Setter
@Entity
@Table(name = "tb_companys")
@NoArgsConstructor
public class Company extends AbstractPersistable<Long> {

  private String name;
}
