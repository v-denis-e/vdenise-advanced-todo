package kz.vdenise.vdeniseadvancedtodo.staff.place.controllers.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kz.vdenise.vdeniseadvancedtodo.model.ErrorDTO;
import kz.vdenise.vdeniseadvancedtodo.model.ListOfObjects;
import kz.vdenise.vdeniseadvancedtodo.model.SingleObject;
import kz.vdenise.vdeniseadvancedtodo.staff.place.exceptions.RegionNotFoundStaffException;
import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.RegionDTO;
import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.RegionWithDistrictDTO;
import kz.vdenise.vdeniseadvancedtodo.staff.place.services.RegionService;
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
public class RegionController {

    private final RegionService service;

    @Operation(description = "Get countries regions", tags = {"Places"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Country found"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Country not found",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))
            )
    })
    @GetMapping(value = "/{countryId}/region", produces = MediaType.APPLICATION_JSON_VALUE)
    public ListOfObjects<RegionDTO> getRegionsByCountryId(@PathVariable Long countryId) {
        var regions = service.findAllRegionsByCountry(countryId);
        regions.forEach(region ->
                region.add(linkTo(methodOn(RegionController.class).getRegionById(region.getId())).withSelfRel())
        );
        var result = new ListOfObjects<>(regions);
        result.add(linkTo(methodOn(RegionController.class).getRegionsByCountryId(countryId)).withSelfRel());
        return result;
    }

    @Operation(description = "Get region by id", tags = {"Places"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Region found"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Region not found",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class))
            )
    })
    @GetMapping(value = "/region/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SingleObject<RegionWithDistrictDTO> getRegionById(@PathVariable Long id) {
        var region = service.findRegionById(id)
                .orElseThrow(() -> new RegionNotFoundStaffException(id));
        region.getDistricts().forEach(district ->
                district.add(linkTo(methodOn(DistrictController.class).getDistrictById(district.getId())).withSelfRel())
        );
        var result = new SingleObject<>(region);
        result.add(linkTo(methodOn(RegionController.class).getRegionById(id)).withSelfRel());
        return result;
    }

}
