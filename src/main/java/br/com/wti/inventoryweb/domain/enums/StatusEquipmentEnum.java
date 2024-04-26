package br.com.wti.inventoryweb.domain.enums;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.Getter;

/**
 * @author Washington Antunes for wTI on 08/04/2023
 */
public enum StatusEquipmentEnum {
  STAND_BY("Disponível", "GREEN"),
  IN_USE("Em Uso", "ORANGE"),
  DISABLED("Desativado", "BLUE"),
  DISCARDED("Descartado", "RED");

  @Getter
  private String description;

  @Getter
  private String color;

  StatusEquipmentEnum(String description, String color) {
    this.description = description;
    this.color = color;
  }

  public static List<StatusEquipmentEnum> typesEntity() {
    return Lists.newArrayList(STAND_BY, IN_USE, DISABLED, DISCARDED);
  }
}
