package br.com.wti.inventoryweb.domain.model;

import br.com.wti.inventoryweb.domain.enums.LocalizacaoEnum;
import br.com.wti.inventoryweb.domain.enums.StatusEquipamentoEnum;
import br.com.wti.inventoryweb.domain.enums.TipoComputadorEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Washington Antunes for wTI on 26/04/2024
 */
@Audited
@Getter
@Setter
@Entity
@Table(name = "tb_computador")
public class Computador extends AbstractPersistable<Long>{

    public void setId(Long id) {//metodo criado pois ao tentar salvar gerar um id
        super.setId(id);
    }

    @Column(name = "numero_serie", nullable = false, unique = true)
    private String numeroSerie;

    @Column(name = "numero_patrimonio", nullable = false, unique = true)
    private String numeroPatrimonio;

    @Column(name = "fabricante", nullable = false)
    private String fabricante;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "data_entrada", nullable = false)
    private LocalDateTime dataEntrada;

    @Enumerated(EnumType.STRING)
    @Column(name = "localizacao", nullable = false)
    private LocalizacaoEnum localizacao;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "valor")
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusEquipamentoEnum status;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoComputadorEnum tipo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "endereco_mac")
    private String enderecoMAC;

    @Column(name = "memoria_ram")
    private String memoriaRam;

    @Column(name = "disco_rigido")
    private String discoRigido;

    @ManyToOne
    @JoinColumn(name = "nota_fiscal_id", nullable = false)
    private NotaFiscal notaFiscal;

    //TODO Adicionar Projeto, Usuario e Estação de Trabalho

    @Column(name = "projeto_id")
    private String projeto;

    @Column(name = "usuario_id")
    private String usuario;

    @Column(name = "estacao_id")
    private String estacao;
}
