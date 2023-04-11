package br.com.wti.inventoryweb.domain.model;

import br.com.wti.inventoryweb.domain.enums.TypeChangeEnum;
import br.com.wti.inventoryweb.domain.enums.TypeEntityEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Washington Antunes for wTI on 11/04/2023
 */
@Table(name = "tb_changes")
@Entity(name = "Change")
@Getter
@NoArgsConstructor(staticName = "of")
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Change {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

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
