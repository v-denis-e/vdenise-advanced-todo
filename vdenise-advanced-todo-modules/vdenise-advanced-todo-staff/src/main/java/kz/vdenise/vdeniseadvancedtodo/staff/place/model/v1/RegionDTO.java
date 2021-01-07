package kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Schema(name = "Region")
public class RegionDTO extends RepresentationModel<RegionDTO> {

    @NotNull
    @Positive
    @NonNull
    private Long id;
    @NotBlank
    @Size(min = 1, max = 1024)
    @NonNull
    private String name;

}
