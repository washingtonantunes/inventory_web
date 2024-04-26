package br.com.wti.inventoryweb.domain.enums;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.Getter;

/**
 * @author Washington Antunes for wTI on 11/04/2023
 */
public enum TypeChangeEnum {
  ENTRY("Entrada"),
  UPDATE("Alteração"),
  DEACTIVATION("Desativação");

  @Getter
  private String description;

  TypeChangeEnum(String description) {
    this.description = description;
  }

  public static List<TypeChangeEnum> typesEntity() {
    return Lists.newArrayList(ENTRY, UPDATE, DEACTIVATION);
  }
}
