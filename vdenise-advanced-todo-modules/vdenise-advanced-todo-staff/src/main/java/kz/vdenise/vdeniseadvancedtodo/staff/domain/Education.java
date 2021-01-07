package kz.vdenise.vdeniseadvancedtodo.staff.domain;

import kz.vdenise.vdeniseadvancedtodo.staff.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(schema = "staff")
public class Education extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "education_type_id")
    private EducationType type;

    @Size(max = 32)
    private String diplomaNumber;
    @NotNull
    @Positive
    private Integer startYear;
    @Positive
    private Integer endYear;

    @NotNull
    private Boolean isGraduated;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;
    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Builder
    public Education(
            Long id,
            @NotNull EducationType type,
            @Size(max = 32) String diplomaNumber,
            @NotNull @Positive Integer startYear,
            @Positive Integer endYear,
            @NotNull Boolean isGraduated,
            Institution institution,
            Speciality speciality,
            Employee employee
    ) {
        this.setId(id);
        this.setType(type);
        this.diplomaNumber = diplomaNumber;
        this.startYear = startYear;
        this.endYear = endYear;
        this.isGraduated = isGraduated;
        this.institution = institution;
        this.speciality = speciality;
        this.employee = employee;
    }

    public void setType(EducationType type) {
        type.getEducations().add(this);
        this.type = type;
    }
}
