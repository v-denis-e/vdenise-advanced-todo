package kz.vdenise.vdeniseadvancedtodo.staff.domain;

import kz.vdenise.vdeniseadvancedtodo.staff.common.domain.BaseEntity;
import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.Country;
import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.District;
import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.Region;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"contacts", "educations"})
@ToString(callSuper = true, exclude = {"contacts", "educations"})
@Entity
@Table(schema = "staff")
public class Employee extends BaseEntity {

    @NonNull
    @NotBlank
    @Size(min = 1, max = 255)
    private String firstName;
    @Size(max = 255)
    private String lastName;
    @Size(max = 255)
    private String middleName;
    @NonNull
    @NotNull
    private LocalDate birtDate;
    private LocalDate employmentDate;
    @NonNull
    @NotNull
    private Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "address_country_id")
    private Country addressCountry;
    @ManyToOne
    @JoinColumn(name = "address_region_id")
    private Region addressRegion;
    @ManyToOne
    @JoinColumn(name = "address_district_id")
    private District addressDistrict;
    @Size(max = 1024)
    private String addressValue;
    @Size(max = 32)
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<Contact> contacts = new HashSet<>();
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<Education> educations = new HashSet<>();

    @Builder
    public Employee(
            Long id,
            @NonNull @NotBlank @Size(min = 1, max = 255) String firstName,
            @Size(max = 255) String lastName,
            @Size(max = 255) String middleName,
            @NonNull @NotNull LocalDate birtDate,
            LocalDate employmentDate,
            @NonNull @NotNull Boolean isActive,
            Country addressCountry,
            Region addressRegion,
            District addressDistrict,
            @Size(max = 1024) String addressValue,
            @Size(max = 32) String zipCode,
            Gender gender,
            Position position,
            Department department
    ) {
        this.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birtDate = birtDate;
        this.employmentDate = employmentDate;
        this.isActive = isActive;
        this.addressCountry = addressCountry;
        this.addressRegion = addressRegion;
        this.addressDistrict = addressDistrict;
        this.addressValue = addressValue;
        this.zipCode = zipCode;
        this.setGender(gender);
        this.setPosition(position);
        this.setDepartment(department);
    }

    public void setGender(Gender gender) {
        gender.getEmployees().add(this);
        this.gender = gender;
    }

    public void setPosition(Position position) {
        position.getEmployees().add(this);
        this.position = position;
    }

    public void setDepartment(Department department) {
        department.getEmployees().add(this);
        this.department = department;
    }

}
