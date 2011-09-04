package ro.satrapu.homebudget.services.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import ro.satrapu.homebudget.services.persistence.ManagedEntity;

/**
 *
 * @author satrapu
 */
@Entity
@Table(name = "CATEGORIES",
       uniqueConstraints =
@UniqueConstraint(columnNames = {"NAME"}))
@Data
public class Category
        implements ManagedEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    @Size(min = 2, max = 200)
    @Column(nullable = false, length = 200, name = "NAME")
    private String name;
}
