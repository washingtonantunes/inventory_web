package br.com.wti.inventoryweb.service;

import br.com.wti.inventoryweb.domain.dto.EntidadeComRevisao;
import br.com.wti.inventoryweb.domain.enums.StatusEquipamentoEnum;
import br.com.wti.inventoryweb.domain.model.Computador;
import br.com.wti.inventoryweb.domain.model.RepositoryDataModel;
import br.com.wti.inventoryweb.exception.NegocioException;
import br.com.wti.inventoryweb.repository.ComputadorRepository;
import br.com.wti.inventoryweb.repository.RevisaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Washington Antunes for wTI on 26/04/2024
 */
@Service
public class ComputadorService {

    @Autowired
    private ComputadorRepository computadorRepository;

    @Autowired
    private RevisaoRepository revisaoRepository;

    public RepositoryDataModel<Computador, Long> procurarComputadores(final Specification<Computador> specification, final Sort sort) {
        return new RepositoryDataModel<>(computadorRepository, specification, sort);
    }

    public Computador buscarComputador(Long computadorId) {
        return computadorRepository.findById(computadorId).orElseThrow(EntityNotFoundException::new);
    }

    public Computador salvarComputador(Computador computador) {
        return computadorRepository.save(computador);
    }

    public Computador desativarComputador(Computador computador) {

        if(computador.getStatus().equals(StatusEquipamentoEnum.DESATIVADO)) {
            throw new NegocioException("Computador já esta desativado!");
        }

        if(computador.getStatus().equals(StatusEquipamentoEnum.DESCARTADO)) {
            throw new NegocioException("Computador não pode ser desativado com o status 'Discartado'.");
        }

        if(computador.getStatus().equals(StatusEquipamentoEnum.EM_USO)) {
            throw new NegocioException("Computador não pode ser desativado com o status 'Em Uso'.");
        }

        computador.setStatus(StatusEquipamentoEnum.DESATIVADO);
        return computadorRepository.save(computador);
    }

    public List<EntidadeComRevisao<Computador>> buscarAuditoria(Long id) {
        return revisaoRepository.listarRevisoes(id, Computador.class);
    }
}
