package ua.com.alevel.persistence.entity.user;

import lombok.Getter;
import lombok.Setter;

import ua.com.alevel.persistence.listener.AgeByBirthDayGenerationListener;
import ua.com.alevel.persistence.listener.FullNameGenerationListener;
import ua.com.alevel.persistence.type.GenderType;
import ua.com.alevel.persistence.type.RoleType;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Date;

@Getter
@Setter
@Entity
@DiscriminatorValue("PERSONAL")
@EntityListeners({
        FullNameGenerationListener.class,
        AgeByBirthDayGenerationListener.class
})
public class PersonalEntity extends UserEntity {

    @Column(name = "GENDER_TYPE")
    @Enumerated(value = EnumType.STRING)
    private GenderType genderType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BIRTH_DAY")
    private Date birthDay;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Transient
    private String fullName;

    @Transient
    private Integer age;

    public PersonalEntity() {
        super();
        setRoleType(RoleType.ROLE_PERSONAL);
    }
}
