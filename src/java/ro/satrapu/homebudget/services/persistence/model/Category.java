package ro.satrapu.homebudget.services.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author satrapu
 */
@Entity
@Table(name = "CATEGORIES", uniqueConstraints =
@UniqueConstraint(columnNames = {"NAME"}))
@Data
@EqualsAndHashCode(callSuper = true)
public class Category extends EntityBase {

    @NotNull
    @Size(min = 2, max = 200)
    @Column(nullable = false, length = 200, name = "NAME")
    private String name;
}
