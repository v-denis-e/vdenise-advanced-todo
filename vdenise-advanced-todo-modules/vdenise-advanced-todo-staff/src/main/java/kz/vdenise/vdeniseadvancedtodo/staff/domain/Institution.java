package kz.vdenise.vdeniseadvancedtodo.staff.domain;

import kz.vdenise.vdeniseadvancedtodo.staff.common.domain.NamedEntity;
import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.Country;
import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.District;
import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.Region;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(schema = "staff")
public class Institution extends NamedEntity {

    @Size(max = 1024)
    private String url;

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
    @JoinColumn(name = "institution_type_id")
    private InstitutionType type;

    @Builder
    public Institution(
            Long id,
            String name,
            String url,
            Country addressCountry,
            Region addressRegion,
            District addressDistrict,
            String addressValue,
            String zipCode
    ) {
        this.setId(id);
        this.setName(name);
        this.url = url;
        this.addressCountry = addressCountry;
        this.addressRegion = addressRegion;
        this.addressDistrict = addressDistrict;
        this.addressValue = addressValue;
        this.zipCode = zipCode;
    }

}
