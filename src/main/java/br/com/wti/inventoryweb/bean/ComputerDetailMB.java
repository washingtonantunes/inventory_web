package br.com.wti.inventoryweb.bean;

import br.com.wti.inventoryweb.domain.model.Computer;
import br.com.wti.inventoryweb.service.ComputerService;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Washington Antunes for wTI on 15/04/2023
 */
@Getter
@Setter
@Component
@ViewScoped
public class ComputerDetailMB extends BaseMB {

  @Autowired
  private ComputerService computerService;

  private Computer model;

  private Long computerId;

  private boolean paramReadonly;

  public void init() {

    Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    computerId = (Long) flash.get("computerId");

    if(computerId != null) {
      model = computerService.findById(computerId);
    }
  }

  public void edit() {
    paramReadonly = true;

    System.out.println(computerId);
    System.out.println(paramReadonly);

    System.out.println(model.getSerialNumber());
    System.out.println("botão edit");
  }

  public void deactivate() {
    System.out.println("botão deactivate");
  }

  public void historic() {
    System.out.println("botão historic");
  }

  public void save() {
    paramReadonly = false;

    System.out.println(computerId);
    System.out.println(paramReadonly);

    System.out.println(model.getSerialNumber());
    System.out.println("botão save");
  }

  public void cancel() {
    paramReadonly = false;

//    model = computerService.findById(computerId);
  }
}
