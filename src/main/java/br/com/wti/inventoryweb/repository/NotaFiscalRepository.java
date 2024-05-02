package br.com.wti.inventoryweb.repository;

import br.com.wti.inventoryweb.domain.model.NotaFiscal;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Washington Antunes for wTI on 01/05/2024
 */
public interface NotaFiscalRepository extends JpaSpecificationRepository<NotaFiscal, Long> {

    @Query("SELECT new br.com.wti.inventoryweb.domain.model.NotaFiscal(n.id, n.numero) FROM NotaFiscal n")
    List<NotaFiscal> buscarNotasFiscaisParaComboBox();
}
