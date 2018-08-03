package hu.krisztn.entity.user;

import hu.krisztn.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Created by Norbert Kriszt (norbert.kriszt@gmail.com) on 2018. 08. 03..
 */
@Entity
@Getter
@Setter
@Table(name = "USER")
public class User extends BaseEntity {

    @Id
    @Column(name = "ID", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "BIRTH_DATE", nullable = false)
    private LocalDate birthDate;
}
