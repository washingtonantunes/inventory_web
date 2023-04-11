package br.com.wti.inventoryweb.domain.enums;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.Getter;

/**
 * @author Washington Antunes for wTI on 11/04/2023
 */
public enum TypeEntityEnum {

  PROJECT("Projeto"),
  COMPUTER("Computador"),
  MONITOR("Monitor"),
  USER("Usuário");


  @Getter
  private String description;

  TypeEntityEnum(String description) {
    this.description = description;
  }

  public static List<TypeEntityEnum> typesEntity() {
    return Lists.newArrayList(PROJECT, COMPUTER, MONITOR, USER);
  }
}
