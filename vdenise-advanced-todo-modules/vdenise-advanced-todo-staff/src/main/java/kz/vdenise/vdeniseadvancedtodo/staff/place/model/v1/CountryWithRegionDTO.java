package kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, exclude = {"regions"})
@ToString(callSuper = true, exclude = {"regions"})
@Data
@NoArgsConstructor
@Schema(name = "CountryWithRegion")
public class CountryWithRegionDTO extends CountryDTO {

    private Set<RegionDTO> regions = new HashSet<>();

    @Builder
    public CountryWithRegionDTO(Long id, String name) {
        setId(id);
        setName(name);
    }

}
