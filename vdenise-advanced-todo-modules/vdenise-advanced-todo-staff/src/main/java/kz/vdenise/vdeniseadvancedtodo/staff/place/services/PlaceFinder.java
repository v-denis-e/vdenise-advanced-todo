package kz.vdenise.vdeniseadvancedtodo.staff.place.services;

import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.CountryWithRegionDTO;
import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.DistrictDTO;
import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.RegionWithDistrictDTO;

import java.util.Optional;

public interface PlaceFinder {
    Optional<CountryWithRegionDTO> findCountryById(Long id);
    Optional<RegionWithDistrictDTO> findRegionById(Long id);
    Optional<DistrictDTO> findDistrictById(Long id);
}
