package br.com.wti.inventoryweb.service;

import br.com.wti.inventoryweb.domain.enums.TypeChangeEnum;
import br.com.wti.inventoryweb.domain.enums.TypeEntityEnum;
import br.com.wti.inventoryweb.domain.form.SearchChangeForm;
import br.com.wti.inventoryweb.domain.model.Change;
import br.com.wti.inventoryweb.repository.ChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Washington Antunes for wTI on 11/04/2023
 */
@Service
public class ChangeService {

  @Autowired
  private ChangeRepository changeRepository;

  public Page<Change> findChangeByParams(SearchChangeForm searchChangeForm, Pageable pageable) {
    return changeRepository.findAll(searchChangeForm.toSpec(), pageable);
  }

  @Transactional
  public void createChange(String description, TypeEntityEnum typeEntity, TypeChangeEnum typeChange, String author) {
    var change = new Change(description, typeEntity, typeChange, author);
    changeRepository.save(change);
  }
}
