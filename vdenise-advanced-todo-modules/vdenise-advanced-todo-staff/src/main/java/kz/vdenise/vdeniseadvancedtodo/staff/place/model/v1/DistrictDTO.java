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
@Schema(name = "District")
public class DistrictDTO extends RepresentationModel<DistrictDTO> {

    @NotNull
    @Positive
    @NonNull
    private Long id;
    @Size(min = 1, max = 1024)
    @NotBlank
    @NonNull
    private String name;

}
