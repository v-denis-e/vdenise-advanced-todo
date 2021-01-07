package kz.vdenise.vdeniseadvancedtodo.staff.domain;

import kz.vdenise.vdeniseadvancedtodo.staff.common.domain.NamedEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "employees")
@ToString(callSuper = true, exclude = "employees")
@Entity
@Table(schema = "staff")
public class Gender extends NamedEntity {

    @OneToMany(mappedBy = "gender")
    private Set<Employee> employees = new HashSet<>();

    @Builder
    public Gender(Long id, String name) {
        this.setId(id);
        this.setName(name);
    }

}
