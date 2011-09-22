package ro.satrapu.homebudget.services.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author satrapu
 */
@Entity
@Table(name = "EXPENSES")
@Data
@EqualsAndHashCode(callSuper = true)
public class Expense
        extends MoneyAmount {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
