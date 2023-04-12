package br.com.wti.inventoryweb.repository;

import br.com.wti.inventoryweb.domain.model.Change;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Washington Antunes for wTI on 11/04/2023
 */
public interface ChangeRepository extends JpaSpecificationRepository<Change, Long> {

}
