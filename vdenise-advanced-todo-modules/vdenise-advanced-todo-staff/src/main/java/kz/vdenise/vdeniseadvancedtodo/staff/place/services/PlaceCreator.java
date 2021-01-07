package kz.vdenise.vdeniseadvancedtodo.staff.place.services;

import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.Country;

import java.util.Set;

public interface PlaceCreator {
    void creteAll(Set<Country> countries);
}
