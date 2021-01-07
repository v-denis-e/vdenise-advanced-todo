package kz.vdenise.vdeniseadvancedtodo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Error")
public class ErrorDTO {

    private String message;
    private List<String> errors;

}
