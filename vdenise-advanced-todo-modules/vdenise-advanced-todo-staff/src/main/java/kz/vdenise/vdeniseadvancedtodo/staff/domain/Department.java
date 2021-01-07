package kz.vdenise.vdeniseadvancedtodo.staff.domain;

import kz.vdenise.vdeniseadvancedtodo.staff.common.domain.NamedEntity;
import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.Country;
import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.District;
import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.Region;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"children", "employees"})
@ToString(callSuper = true, exclude = {"children", "employees"})
@Entity
@Table(schema = "staff")
public class Department extends NamedEntity {

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
    @JoinColumn(name = "parent_id")
    private Department parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<Department> children = new HashSet<>();

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees = new HashSet<>();

    @Builder
    public Department(
            Long id,
            String name,
            Boolean isActive,
            Country addressCountry,
            Region addressRegion,
            District addressDistrict,
            String addressValue,
            String zipCode
    ) {
        this.setId(id);
        this.setName(name);
        this.isActive = isActive;
        this.addressCountry = addressCountry;
        this.addressRegion = addressRegion;
        this.addressDistrict = addressDistrict;
        this.addressValue = addressValue;
        this.zipCode = zipCode;
    }

    public void addChild(Department department) {
        department.setParent(this);
        this.children.add(department);
    }

}
