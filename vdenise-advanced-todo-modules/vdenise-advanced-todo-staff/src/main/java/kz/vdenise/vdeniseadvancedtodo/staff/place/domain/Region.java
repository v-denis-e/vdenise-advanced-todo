package kz.vdenise.vdeniseadvancedtodo.staff.place.domain;

import kz.vdenise.vdeniseadvancedtodo.staff.common.domain.NamedEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "districts")
@ToString(callSuper = true, exclude = "districts")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(schema = "staff")
public class Region extends NamedEntity {

    @NonNull
    @NotNull
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private Set<District> districts;

    @Builder
    public Region(Long id, String name) {
        this.setId(id);
        this.setName(name);
        this.districts = new HashSet<>();
    }

    public void addDistrict(District district) {
        district.setRegion(this);
        this.districts.add(district);
    }

}
