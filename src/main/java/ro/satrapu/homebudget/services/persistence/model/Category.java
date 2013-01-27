package ro.satrapu.homebudget.services.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author satrapu
 */
@Entity
@Table(name = "Categories", uniqueConstraints =
@UniqueConstraint(columnNames = {"Name"}))
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Category extends ManagedEntity {

    private static final long serialVersionUID = 1L;
    @NotNull
    @Size(min = 2, max = 200)
    @Column(nullable = false, length = 200, name = "Name")
    private String name;
}
