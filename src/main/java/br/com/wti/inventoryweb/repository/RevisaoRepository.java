package br.com.wti.inventoryweb.repository;

import br.com.wti.inventoryweb.domain.dto.EntidadeComRevisao;
import br.com.wti.inventoryweb.domain.model.Revisao;
import br.com.wti.inventoryweb.domain.enums.TipoRevisaoEnum;
import com.google.common.collect.Lists;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Washington Antunes for wTI on 04/05/2024
 */
@Repository
@Transactional
public class RevisaoRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public <T> List<EntidadeComRevisao<T>> listarRevisoes(Long id, Class<T> classe) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        List<Number> idsRevisao = auditReader.getRevisions(classe, id);
        List<EntidadeComRevisao<T>> todasRevisoes = Lists.newArrayList();

        for(Number revisao : idsRevisao) {
            Object[] revision = (Object[]) auditReader.createQuery()
                    .forRevisionsOfEntity(classe, false, true)
                    .add(AuditEntity.revisionNumber().eq(revisao))
                    .getSingleResult();

            T entidade = (T) revision[0];
            Revisao r = (Revisao) revision[1];
            RevisionType tipo = (RevisionType) revision[2];

            TipoRevisaoEnum tipoRevisao = TipoRevisaoEnum.buscarPorTipo(tipo);
            todasRevisoes.add(new EntidadeComRevisao<>(r, tipoRevisao, entidade));
        }

        return todasRevisoes;
    }
}
