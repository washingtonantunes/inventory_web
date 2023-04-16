package br.com.wti.inventoryweb.bean;

import br.com.wti.inventoryweb.domain.model.Computer;
import br.com.wti.inventoryweb.repository.ComputerRepository;
import br.com.wti.inventoryweb.service.ComputerService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

  private String computerId;

  private boolean paramReadonly;

  public void init() {

    System.out.println(computerId);
    System.out.println(paramReadonly);

    if (computerId != null) {
      model = computerService.findById(Integer.parseInt(computerId));
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

    System.out.println(computerId);
    System.out.println(paramReadonly);

    System.out.println("botão cancel");
  }
}
