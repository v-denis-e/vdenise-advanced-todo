package kz.vdenise.vdeniseadvancedtodo.staff.place.mappers.v1;

import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.Country;
import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.District;
import kz.vdenise.vdeniseadvancedtodo.staff.place.domain.Region;
import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.*;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PlaceMapper {
    @IterableMapping(qualifiedByName = "regionToRegionDTO")
    CountryWithRegionDTO countryToCountryWithRegionDTO(Country country);
    CountryDTO countryToCountryDTO(Country country);
    @Named("regionToRegionDTO")
    RegionDTO regionToRegionDTO(Region region);
    DistrictDTO districtToDistrictDTO(District district);
    RegionWithDistrictDTO regionToRegionWithDistrictDTO(Region region);
}
