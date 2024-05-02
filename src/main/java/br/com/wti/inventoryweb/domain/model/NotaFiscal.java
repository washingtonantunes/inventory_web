package br.com.wti.inventoryweb.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Washington Antunes for wTI on 01/05/2024
 */
@Getter
@Setter
@Entity
@Table(name = "tb_nota_fiscal")
public class NotaFiscal extends AbstractPersistable<Long> {

    public void setId(Long id) {//metodo criado pois ao tentar salvar gerar um id
        super.setId(id);
    }

    @Column(name = "numero", unique = true, nullable = false)
    private String numero;

    @Column(name = "dataEntrada", nullable = false)
    private LocalDateTime dataEntrada;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "empresa_id", nullable = false)
    private String empresa;

    public NotaFiscal() {
    }

    public NotaFiscal(Long id, String numero) {
        this.setId(id);
        this.numero = numero;
    }
}
