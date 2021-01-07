package kz.vdenise.vdeniseadvancedtodo.staff.place.services;

import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.DistrictDTO;

import java.util.Optional;
import java.util.Set;

public interface DistrictService {
    Set<DistrictDTO> findAllDistrictsByRegion(Long region);
    Optional<DistrictDTO> findDistrictById(Long id);
}
