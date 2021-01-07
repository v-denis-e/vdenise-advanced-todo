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
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(schema = "staff")
public class EducationType extends NamedEntity {

    @OneToMany(mappedBy = "type")
    private Set<Education> educations = new HashSet<>();

    @Builder
    public EducationType(Long id, String name) {
        this.setId(id);
        this.setName(name);
    }

}
