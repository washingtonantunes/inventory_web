package br.com.wti.inventoryweb.service;

import br.com.wti.inventoryweb.domain.dto.EntidadeComRevisao;
import br.com.wti.inventoryweb.domain.enums.StatusEquipamentoEnum;
import br.com.wti.inventoryweb.domain.model.Monitor;
import br.com.wti.inventoryweb.domain.model.RepositoryDataModel;
import br.com.wti.inventoryweb.exception.NegocioException;
import br.com.wti.inventoryweb.repository.MonitorRepository;
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
public class MonitorService {

    @Autowired
    private MonitorRepository monitorRepository;

    @Autowired
    private RevisaoRepository revisaoRepository;

    public RepositoryDataModel<Monitor, Long> procurarMonitores(final Specification<Monitor> specification, final Sort sort) {
        return new RepositoryDataModel<>(monitorRepository, specification, sort);
    }

    public Monitor buscarMonitor(Long monitorId) {
        return monitorRepository.findById(monitorId).orElseThrow(EntityNotFoundException::new);
    }

    public Monitor salvarMonitor(Monitor monitor) {
        return monitorRepository.save(monitor);
    }

    public Monitor desativarMonitor(Monitor monitor) {

        if(monitor.getStatus().equals(StatusEquipamentoEnum.DESATIVADO)) {
            throw new NegocioException("Monitor já esta desativado!");
        }

        if(monitor.getStatus().equals(StatusEquipamentoEnum.DESCARTADO)) {
            throw new NegocioException("Monitor não pode ser desativado com o status 'Discartado'.");
        }

        if(monitor.getStatus().equals(StatusEquipamentoEnum.EM_USO)) {
            throw new NegocioException("Monitor não pode ser desativado com o status 'Em Uso'.");
        }

        monitor.setStatus(StatusEquipamentoEnum.DESATIVADO);
        return monitorRepository.save(monitor);
    }

    public List<EntidadeComRevisao<Monitor>> buscarAuditoria(Long id) {
        return revisaoRepository.listarRevisoes(id, Monitor.class);
    }
}
