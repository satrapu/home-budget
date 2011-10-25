package ro.satrapu.homebudget.services.persistence;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * Base class for any entity.
 * @author satrapu
 */
@MappedSuperclass
@Data
public abstract class ManagedEntity implements Entity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PRIVATE)
    private Long id;
    @Version
    @Setter(AccessLevel.PRIVATE)
    private Long version;

    @Override
    public boolean isManaged() {
        return getId() != null;
    }
}
