package kz.vdenise.vdeniseadvancedtodo.model;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class ListOfObjects<T> extends SingleObject<Set<T>> {

    public ListOfObjects(Set<T> data) {
        super(data);
    }

}
