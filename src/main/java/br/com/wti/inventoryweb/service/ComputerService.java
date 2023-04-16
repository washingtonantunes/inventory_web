package br.com.wti.inventoryweb.service;

import br.com.wti.inventoryweb.domain.model.Computer;
import br.com.wti.inventoryweb.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Washington Antunes for wTI on 16/04/2023
 */
@Service
public class ComputerService {

  @Autowired
  private ComputerRepository computerRepository;

  public Computer findById(long computerId) {
    return computerRepository.getReferenceById(computerId);
  }
}
