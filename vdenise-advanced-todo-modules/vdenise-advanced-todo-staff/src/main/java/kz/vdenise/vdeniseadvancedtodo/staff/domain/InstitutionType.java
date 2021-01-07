package kz.vdenise.vdeniseadvancedtodo.staff.domain;

import kz.vdenise.vdeniseadvancedtodo.staff.common.domain.NamedEntity;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "institutions")
@ToString(callSuper = true, exclude = "institutions")
@Entity
@Table(schema = "staff")
public class InstitutionType extends NamedEntity {

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private Set<Institution> institutions = new HashSet<>();

    @Builder
    public InstitutionType(Long id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public void addInstitution(Institution institution) {
        institution.setType(this);
        this.institutions.add(institution);
    }

}
