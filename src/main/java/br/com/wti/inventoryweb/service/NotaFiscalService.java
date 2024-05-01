package br.com.wti.inventoryweb.service;

import br.com.wti.inventoryweb.domain.model.NotaFiscal;
import br.com.wti.inventoryweb.domain.model.RepositoryDataModel;
import br.com.wti.inventoryweb.repository.NotaFiscalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author Washington Antunes for wTI on 01/05/2024
 */
@Service
public class NotaFiscalService {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    public RepositoryDataModel<NotaFiscal, Long> procurarNotasFiscais(final Specification<NotaFiscal> specification, final Sort sort) {
        return new RepositoryDataModel<>(notaFiscalRepository, specification, sort);
    }

    public NotaFiscal buscarNotaFiscal(Long notaFiscalId) {
        return notaFiscalRepository.findById(notaFiscalId).orElseThrow(EntityNotFoundException::new);
    }
}
