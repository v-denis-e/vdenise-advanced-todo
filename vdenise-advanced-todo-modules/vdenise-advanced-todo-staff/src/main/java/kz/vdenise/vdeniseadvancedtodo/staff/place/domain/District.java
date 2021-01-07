package kz.vdenise.vdeniseadvancedtodo.staff.place.domain;

import kz.vdenise.vdeniseadvancedtodo.staff.common.domain.NamedEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(schema = "staff")
public class District extends NamedEntity {

    @NonNull
    @NotNull
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @Builder
    public District(Long id, String name) {
        this.setId(id);
        this.setName(name);
    }

}
