package kz.vdenise.vdeniseadvancedtodo.staff.place.controllers.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.vdenise.vdeniseadvancedtodo.model.ErrorDTO;
import kz.vdenise.vdeniseadvancedtodo.model.ListOfObjects;
import kz.vdenise.vdeniseadvancedtodo.model.SingleObject;
import kz.vdenise.vdeniseadvancedtodo.staff.place.exceptions.CountryNotFoundStaffException;
import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.CountryDTO;
import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.CountryWithRegionDTO;
import kz.vdenise.vdeniseadvancedtodo.staff.place.services.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/staff/place")
@Tag(name = "Places", description = "Dictionaries of administrative divisions")
public class CountryController {

    private final CountryService service;

    @Operation(description = "Get all countries", tags = {"Places"})
    @ApiResponse(responseCode = "200", description = "All ok!")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ListOfObjects<CountryDTO> getAllCountries() {
        var countries = service.findAllCountries();
        countries.forEach(country ->
                country.add(linkTo(methodOn(CountryController.class).getCountryById(country.getId())).withSelfRel())
        );
        var result = new ListOfObjects<>(countries);
        result.add(linkTo(methodOn(CountryController.class).getAllCountries()).withSelfRel());
        return result;
    }

    @Operation(description = "Get country by id", tags = {"Places"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "All ok!"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Country not found",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))
            )
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SingleObject<CountryWithRegionDTO> getCountryById(@PathVariable Long id) {
        var country = service.findCountryById(id)
                .orElseThrow(() -> new CountryNotFoundStaffException(id));
        country.getRegions().forEach(region ->
                region.add(linkTo(methodOn(RegionController.class).getRegionById(region.getId())).withSelfRel())
        );
        var result = new SingleObject<>(country);
        result.add(linkTo(methodOn(CountryController.class).getCountryById(id)).withSelfRel());
        return result;
    }

}
