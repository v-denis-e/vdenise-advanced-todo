package kz.vdenise.vdeniseadvancedtodo.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SingleObject<T> extends RepresentationModel<SingleObject<T>> {

    private T data;

}
