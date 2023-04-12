package br.com.wti.inventoryweb.service;

import br.com.wti.inventoryweb.domain.enums.TypeChangeEnum;
import br.com.wti.inventoryweb.domain.enums.TypeEntityEnum;
import br.com.wti.inventoryweb.domain.model.Change;
import br.com.wti.inventoryweb.repository.ChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Washington Antunes for wTI on 11/04/2023
 */
@Service
public class ChangeService {

  @Autowired
  private ChangeRepository changeRepository;

  @Transactional
  public void createChange(String description, TypeEntityEnum typeEntity, TypeChangeEnum typeChange, String author) {
    var change = new Change(description, typeEntity, typeChange, author);
    changeRepository.save(change);
  }
}
