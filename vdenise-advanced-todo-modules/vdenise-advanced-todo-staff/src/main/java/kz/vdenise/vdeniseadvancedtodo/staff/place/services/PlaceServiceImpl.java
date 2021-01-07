package kz.vdenise.vdeniseadvancedtodo.staff.place.services;

import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.Country;
import kz.vdenise.vdeniseadvancedtodo.staff.place.exceptions.CountryNotFoundStaffException;
import kz.vdenise.vdeniseadvancedtodo.staff.place.exceptions.RegionNotFoundStaffException;
import kz.vdenise.vdeniseadvancedtodo.staff.place.mappers.v1.PlaceMapper;
import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.*;
import kz.vdenise.vdeniseadvancedtodo.staff.place.repositories.CountryRepository;
import kz.vdenise.vdeniseadvancedtodo.staff.place.repositories.DistrictRepository;
import kz.vdenise.vdeniseadvancedtodo.staff.place.repositories.RegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlaceServiceImpl implements PlaceService {

    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final DistrictRepository districtRepository;
    private final PlaceMapper mapper;

    @Override
    public void creteAll(Set<Country> countries) {
        log.debug("Creating countries ...");
        countryRepository.saveAll(countries);
    }

    @Override
    public Optional<CountryWithRegionDTO> findCountryById(Long id) {
        log.debug("Finding country by id {} ...", id);
        return countryRepository.findById(id).map(mapper::countryToCountryWithRegionDTO);
    }

    @Override
    public Optional<RegionWithDistrictDTO> findRegionById(Long id) {
        log.debug("Finding region by id {} ...", id);
        return regionRepository.findById(id).map(mapper::regionToRegionWithDistrictDTO);
    }

    @Override
    public Optional<DistrictDTO> findDistrictById(Long id) {
        log.debug("Finding district by id {} ...", id);
        return districtRepository.findById(id).map(mapper::districtToDistrictDTO);
    }

    @Override
    public Set<CountryDTO> findAllCountries() {
        log.debug("Finding all countries ...");
        return countryRepository.findAll().stream().map(mapper::countryToCountryDTO).collect(Collectors.toSet());
    }

    @Override
    public Set<RegionDTO> findAllRegionsByCountry(Long countryId) {
        log.debug("Finding all regions for country {} ...", countryId);
        return countryRepository.findById(countryId).map(country ->
                country.getRegions().stream().map(mapper::regionToRegionDTO).collect(Collectors.toSet())
        ).orElseThrow(() -> new CountryNotFoundStaffException(countryId));
    }

    @Override
    public Set<DistrictDTO> findAllDistrictsByRegion(Long regionId) {
        log.debug("Finding all districts for region {} ...", regionId);
        return regionRepository.findById(regionId).map(region ->
                region.getDistricts().stream().map(mapper::districtToDistrictDTO).collect(Collectors.toSet())
        ).orElseThrow(() -> new RegionNotFoundStaffException(regionId));
    }
}
