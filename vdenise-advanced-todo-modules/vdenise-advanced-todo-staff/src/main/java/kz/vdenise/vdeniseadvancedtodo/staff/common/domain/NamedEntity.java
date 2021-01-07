package kz.vdenise.vdeniseadvancedtodo.staff.common.domain;

import lombok.*;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class NamedEntity extends BaseEntity {

    @NotBlank
    @Size(min = 1, max = 1024)
    @NonNull
    private String name;

}
