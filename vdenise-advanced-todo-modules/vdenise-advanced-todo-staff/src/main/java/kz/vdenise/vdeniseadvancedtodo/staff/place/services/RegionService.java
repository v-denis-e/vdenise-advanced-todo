package kz.vdenise.vdeniseadvancedtodo.staff.place.services;

import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.RegionDTO;
import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.RegionWithDistrictDTO;

import java.util.Optional;
import java.util.Set;

public interface RegionService {
    Set<RegionDTO> findAllRegionsByCountry(Long countryId);
    Optional<RegionWithDistrictDTO> findRegionById(Long id);
}
