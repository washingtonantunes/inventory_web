package br.com.wti.inventoryweb.domain.model;

import br.com.wti.inventoryweb.domain.enums.TypeChangeEnum;
import br.com.wti.inventoryweb.domain.enums.TypeEntityEnum;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * @author Washington Antunes for wTI on 11/04/2023
 */
@Getter
@Setter
@Entity
@Table(name = "tb_changes")
@NoArgsConstructor(staticName = "of")
@AttributeOverride(name = "id", column = @Column(name = "id_change"))
public class Change extends AbstractPersistable<Long> {

  @Column(name = "date_change", nullable = false)
  private LocalDate date = LocalDate.now();

  @Column(name = "description_change", nullable = false)
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "type_entity", nullable = false)
  private TypeEntityEnum typeEntity;

  @Enumerated(EnumType.STRING)
  @Column(name = "type_change", nullable = false)
  private TypeChangeEnum typeChange;

  @Column(nullable = false)
  private String author; //TODO Alterar para entidade author

  public Change(String description, TypeEntityEnum typeEntity, TypeChangeEnum typeChange, String author) {
    this.description = description;
    this.typeEntity = typeEntity;
    this.typeChange = typeChange;
    this.author = author;
  }
}
