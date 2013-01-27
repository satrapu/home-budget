package ro.satrapu.homebudget.services.persistence.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author satrapu
 */
@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class MoneyAmount extends ManagedEntity {

    @NotNull
    @Size(min = 2, max = 4000)
    @Column(nullable = false, length = 4000, name = "Description")
    private String description;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Category category;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @NotNull
    private Currency currency;
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, name = "CreateDate")
    @Setter(AccessLevel.PRIVATE)
    private Date createDate;
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, name = "InputDate")
    private Date inputDate;
    @NotNull
    @Column(nullable = false, name = "Amount")
    private BigDecimal amount;

    @PrePersist
    protected void onBeforePersist() {
        createDate = new Date();
    }
}
