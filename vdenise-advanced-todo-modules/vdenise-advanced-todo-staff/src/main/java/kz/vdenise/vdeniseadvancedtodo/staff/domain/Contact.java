package kz.vdenise.vdeniseadvancedtodo.staff.domain;

import kz.vdenise.vdeniseadvancedtodo.staff.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(schema = "staff")
public class Contact extends BaseEntity {

    @NonNull
    @NotNull
    @Enumerated(EnumType.STRING)
    private ContactType type;
    @NonNull
    @NotBlank
    @Size(min = 1, max = 255)
    private String title;
    private String description;
    @NonNull
    @NotBlank
    @Size(min = 1, max = 1024)
    private String value;
    @NonNull
    @NotNull
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Builder
    public Contact(Long id, ContactType type, String title, String description, String value, Employee employee) {
        super.setId(id);
        this.type = type;
        this.title = title;
        this.description = description;
        this.value = value;
        this.employee = employee;
    }

}
