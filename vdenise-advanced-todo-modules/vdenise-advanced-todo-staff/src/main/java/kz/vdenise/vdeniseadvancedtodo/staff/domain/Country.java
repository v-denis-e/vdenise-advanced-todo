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
@EqualsAndHashCode(callSuper = true, exclude = {"regions"})
@ToString(callSuper = true, exclude = {"regions"})
@Entity
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
