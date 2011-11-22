package ro.satrapu.homebudget.services.persistence.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author satrapu
 */
@Entity
@Table(name = "INCOMES")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Income extends MoneyAmount {

    private static final long serialVersionUID = 1L;
}
