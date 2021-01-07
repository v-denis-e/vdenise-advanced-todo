package kz.vdenise.vdeniseadvancedtodo.staff.place.services;

import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.Country;
import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.District;
import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.Region;
import kz.vdenise.vdeniseadvancedtodo.staff.place.exceptions.CountryNotFoundStaffException;
import kz.vdenise.vdeniseadvancedtodo.staff.place.exceptions.RegionNotFoundStaffException;
import kz.vdenise.vdeniseadvancedtodo.staff.place.mappers.v1.PlaceMapperImpl;
import kz.vdenise.vdeniseadvancedtodo.staff.place.repositories.CountryRepository;
import kz.vdenise.vdeniseadvancedtodo.staff.place.repositories.DistrictRepository;
import kz.vdenise.vdeniseadvancedtodo.staff.place.repositories.RegionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class PlaceServiceImplTest {

    @Mock
    CountryRepository countryRepository;
    @Mock
    RegionRepository regionRepository;
    @Mock
    DistrictRepository districtRepository;
    @Spy
    PlaceMapperImpl placeMapper;
    @InjectMocks
    PlaceServiceImpl placeService;

    @Test
    void creteAll() {
        var countries = Set.of(
                Country.builder().name("country1").build(),
                Country.builder().name("country2").build()
        );

        placeService.creteAll(countries);

        then(countryRepository).should().saveAll(countries);
    }

    @Test
    void findCountryById() {
        var expected = Country.builder().id(1L).name("country").build();
        given(countryRepository.findById(expected.getId()))
                .willReturn(Optional.of(expected));

        var actual = placeService.findCountryById(expected.getId());

        then(countryRepository).should().findById(expected.getId());
        assertThat(actual).isPresent();
        var countryWithRegion = actual.get();
        assertThat(countryWithRegion.getId()).isEqualTo(expected.getId());
        assertThat(countryWithRegion.getName()).isEqualTo(expected.getName());
    }

    @Test
    void findRegionById() {
        var expected = Region.builder().id(1L).name("region1").build();
        given(regionRepository.findById(expected.getId()))
                .willReturn(Optional.of(expected));

        var actual = placeService.findRegionById(expected.getId());

        then(regionRepository).should().findById(expected.getId());
        assertThat(actual).isPresent();
        var regionWithDistrict = actual.get();
        assertThat(regionWithDistrict.getId()).isEqualTo(expected.getId());
        assertThat(regionWithDistrict.getName()).isEqualTo(expected.getName());
    }

    @Test
    void findDistrictById() {
        var expected = District.builder().id(1L).name("district1").build();
        given(districtRepository.findById(expected.getId()))
                .willReturn(Optional.of(expected));

        var actual = placeService.findDistrictById(expected.getId());

        then(districtRepository).should().findById(expected.getId());
        assertThat(actual).isPresent();
        var districtDTO = actual.get();
        assertThat(districtDTO.getId()).isEqualTo(expected.getId());
        assertThat(districtDTO.getName()).isEqualTo(expected.getName());
    }

    @Test
    void findAllCountries() {
        var countries = List.of(
                Country.builder().id(1L).name("country1").build(),
                Country.builder().id(2L).name("country2").build()
        );
        given(countryRepository.findAll())
                .willReturn(countries);

        var actual = placeService.findAllCountries();

        then(countryRepository).should().findAll();
        assertThat(actual).hasSize(2);
    }

    @Test
    void findAllRegionsByCountry() {
        var regions = List.of(
                Region.builder().id(1L).name("1").build(),
                Region.builder().id(2L).name("2").build()
        );
        var country = Country.builder().id(1L).name("country").build();
        regions.forEach(country::addRegion);
        given(countryRepository.findById(country.getId()))
                .willReturn(Optional.of(country));

        var actual = placeService.findAllRegionsByCountry(country.getId());

        then(countryRepository).should().findById(country.getId());
        assertThat(actual).hasSize(2);
    }

    @Test
    void findAllRegionsByCountry_countryDoesNotExist() {
        var countryId = 1L;
        given(countryRepository.findById(countryId))
                .willReturn(Optional.empty());

        assertThrows(CountryNotFoundStaffException.class, () -> placeService.findAllRegionsByCountry(countryId));
    }

    @Test
    void findAllDistrictsByRegion() {
        var districts = List.of(
                District.builder().id(1L).name("district1").build(),
                District.builder().id(2L).name("2").build()
        );
        var region = Region.builder().id(14L).name("reg").build();
        districts.forEach(region::addDistrict);
        given(regionRepository.findById(region.getId()))
                .willReturn(Optional.of(region));

        var actual = placeService.findAllDistrictsByRegion(region.getId());

        then(regionRepository).should().findById(region.getId());
        assertThat(actual).hasSize(2);
    }

    @Test
    void findAllDistrictsByRegion_regionDoesNotExist() {
        var regionId = 123L;
        given(regionRepository.findById(regionId))
                .willReturn(Optional.empty());

        assertThrows(RegionNotFoundStaffException.class, () -> placeService.findAllDistrictsByRegion(regionId));
    }
}