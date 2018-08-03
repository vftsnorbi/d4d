package hu.krisztn.entity.base;

import hu.krisztn.entity.util.ClassCache;
import lombok.Getter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Norbert Kriszt (norbert.kriszt@gmail.com) on 2018. 08. 03..
 */
@Getter
@MappedSuperclass
public abstract class BaseEntity implements IEntity {

    @Version
    @Column(name = "VERSION")
    private Long version;

    @Column(name = "CREATED_DATE", nullable = false)
    private LocalDateTime createdDate;

    @PrePersist
    public void prePersist() {
        createdDate = LocalDateTime.now();
    }

    @Transient
    private String uuid = null;

    @Override
    public boolean equals(final Object obj) {
        boolean result = false;
        if (this == obj) {
            result = true;
        } else if (obj != null && (getClass().isInstance(obj) || obj.getClass().isInstance(this))) {
            final BaseEntity that = (BaseEntity) obj; // here it is legal to have AbstractEntity
            result = getId() == null ? getKey().equals(that.getKey()) : getId().equals(that.getId());
        }
        return result;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : super.hashCode();
    }

    @Override
    public String getKey() {
        StringBuilder sb = new StringBuilder(ClassCache.getSimpleName(getClass()));
        sb.append("_");
        sb.append(getId() == null ? getUuid() : getId());
        return sb.toString();
    }

    @XmlTransient
    private String getUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }
        return uuid;
    }
}
