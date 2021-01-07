package kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true, exclude = "districts")
@ToString(callSuper = true, exclude = "districts")
@Data
@NoArgsConstructor
@Schema(name = "RegionWithDistrict")
public class RegionWithDistrictDTO extends RegionDTO {

    private Set<DistrictDTO> districts;

    @Builder
    public RegionWithDistrictDTO(Long id, String name) {
        setId(id);
        setName(name);
    }

}
