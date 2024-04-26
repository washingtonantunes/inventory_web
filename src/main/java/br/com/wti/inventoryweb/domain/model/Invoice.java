package br.com.wti.inventoryweb.domain.model;

import com.google.common.collect.Lists;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * @author Washington Antunes for wTI on 16/04/2023
 */
@Getter
@Setter
@Entity
@Table(name = "tb_invoices")
@NoArgsConstructor
public class Invoice extends AbstractPersistable<Long> {

  @Column(name = "number_invoice", unique = true, nullable = false)
  private String number;

  @Column(name = "date_entry", nullable = false)
  private LocalDate dateEntry;

  @Column(name = "value_invoice", nullable = false)
  private BigDecimal value = BigDecimal.ZERO;

  @ManyToOne
  @JoinColumn(name = "id_company", nullable = false)
  private Company company;

  @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<Computer> itens = Lists.newArrayList();

  public void sumValue(BigDecimal value) {
    this.value = this.value.add(value);
  }
}
