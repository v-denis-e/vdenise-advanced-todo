package kz.vdenise.vdeniseadvancedtodo.staff.domain;

import kz.vdenise.vdeniseadvancedtodo.staff.common.domain.NamedEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(schema = "staff")
public class Speciality extends NamedEntity {

    @Builder
    public Speciality(Long id, String name) {
        this.setId(id);
        this.setName(name);
    }

}
