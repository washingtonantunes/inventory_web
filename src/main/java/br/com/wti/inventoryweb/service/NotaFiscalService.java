package br.com.wti.inventoryweb.service;

import br.com.wti.inventoryweb.domain.dto.EntidadeComRevisao;
import br.com.wti.inventoryweb.domain.model.NotaFiscal;
import br.com.wti.inventoryweb.domain.model.RepositoryDataModel;
import br.com.wti.inventoryweb.repository.NotaFiscalRepository;
import br.com.wti.inventoryweb.repository.RevisaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Washington Antunes for wTI on 01/05/2024
 */
@Service
public class NotaFiscalService {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    @Autowired
    private RevisaoRepository revisaoRepository;

    public RepositoryDataModel<NotaFiscal, Long> procurarNotasFiscais(final Specification<NotaFiscal> specification, final Sort sort) {
        return new RepositoryDataModel<>(notaFiscalRepository, specification, sort);
    }

    public NotaFiscal buscarNotaFiscal(Long notaFiscalId) {
        return notaFiscalRepository.findById(notaFiscalId).orElseThrow(EntityNotFoundException::new);
    }

    public NotaFiscal salvarNotaFiscal(NotaFiscal notaFiscal) {
        return notaFiscalRepository.save(notaFiscal);
    }

    public List<NotaFiscal> buscarNotasFiscaisParaComboBox() {
        return notaFiscalRepository.buscarNotasFiscaisParaComboBox();
    }

    public List<EntidadeComRevisao<NotaFiscal>> buscarAuditoria(Long id) {
        return revisaoRepository.listarRevisoes(id, NotaFiscal.class);
    }
}
