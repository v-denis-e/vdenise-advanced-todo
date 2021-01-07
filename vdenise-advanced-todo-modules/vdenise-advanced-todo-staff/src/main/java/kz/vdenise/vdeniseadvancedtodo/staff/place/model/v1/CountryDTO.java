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
@Schema(name = "Country", description = "Country data")
public class CountryDTO extends RepresentationModel<CountryDTO> {

    @Schema(name = "Unique system id of the country")
    @NotNull
    @Positive
    @NonNull
    private Long id;
    @Schema(name = "Country name")
    @NotBlank
    @Size(min = 1, max = 1024)
    @NonNull
    private String name;

}
