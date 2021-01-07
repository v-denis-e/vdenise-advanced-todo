package kz.vdenise.vdeniseadvancedtodo.staff.place.services;

import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.CountryDTO;
import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.DistrictDTO;
import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.RegionDTO;

import java.util.Set;

public interface PlaceList {
    Set<CountryDTO> findAllCountries();
    Set<RegionDTO> findAllRegionsByCountry(Long countryId);
    Set<DistrictDTO> findAllDistrictsByRegion(Long region);
}
