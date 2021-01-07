package kz.vdenise.vdeniseadvancedtodo.staff.place.services;

import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.CountryDTO;
import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.CountryWithRegionDTO;

import java.util.Optional;
import java.util.Set;

public interface CountryService {
    Set<CountryDTO> findAllCountries();
    Optional<CountryWithRegionDTO> findCountryById(Long id);
}
