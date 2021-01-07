package kz.vdenise.vdeniseadvancedtodo.staff.place.domain;

import kz.vdenise.vdeniseadvancedtodo.staff.common.domain.NamedEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"regions"})
@ToString(callSuper = true, exclude = {"regions"})
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(schema = "staff")
public class Country extends NamedEntity {

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private Set<Region> regions;

    @Builder
    public Country(Long id, String name) {
        this.setId(id);
        this.setName(name);
        this.regions = new HashSet<>();
    }

    public void addRegion(Region region) {
        region.setCountry(this);
        this.regions.add(region);
    }

}
