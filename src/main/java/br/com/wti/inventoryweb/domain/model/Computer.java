package br.com.wti.inventoryweb.domain.model;

import br.com.wti.inventoryweb.domain.enums.LocationEnum;
import br.com.wti.inventoryweb.domain.enums.StatusEquipmentEnum;
import br.com.wti.inventoryweb.domain.enums.TypeComputerEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * @author Washington Antunes for wTI on 12/04/2023
 */
@Getter
@Setter
@Entity
@Table(name = "tb_computers")
@NoArgsConstructor
public class Computer extends AbstractPersistable<Long> {

  @Column(name = "serial_number", unique = true, nullable = false)
  private String serialNumber;

  @Column(name = "patrimony_number", unique = true, nullable = false)
  private String patrimonyNumber;

  @Column(nullable = false)
  private String brand;

  @Column(nullable = false)
  private String model;

  @Column(name = "date_entry", nullable = false)
  private LocalDateTime dateEntry = LocalDateTime.now();

  @ManyToOne
  @JoinColumn(name = "id_invoice", nullable = false)
  private Invoice invoice;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private LocationEnum location = LocationEnum.TI;

  private String note;

  @Column(name = "value_entry", nullable = false, precision = 10, scale = 2)
  private BigDecimal valueEntry;

  @Enumerated(EnumType.STRING)
  @Column(name = "status_computer", nullable = false)
  private StatusEquipmentEnum status = StatusEquipmentEnum.STAND_BY;

  @Enumerated(EnumType.STRING)
  @Column(name = "type_computer", nullable = false)
  private TypeComputerEnum type;

  @Column(name = "host_name")
  private String hostName;

  @Column(name = "address_mac")
  private String addressMAC;

  @Column(name = "memory_ram")
  private String memoryRam;

  @Column(name = "hard_disk")
  private String hardDisk;

  //TODO Adicionar Projeto, Usuario e Estação de Trabalho
  @Column(name = "project_id")
  private String project;

  @Column(name = "user_id")
  private String user;

  @Column(name = "workstation_id")
  private String workstation;
}
