package kz.vdenise.vdeniseadvancedtodo.staff.place.controllers.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kz.vdenise.vdeniseadvancedtodo.model.ListOfObjects;
import kz.vdenise.vdeniseadvancedtodo.model.SingleObject;
import kz.vdenise.vdeniseadvancedtodo.staff.place.exceptions.DistrictNotFoundStaffException;
import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.DistrictDTO;
import kz.vdenise.vdeniseadvancedtodo.staff.place.services.DistrictService;
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
public class DistrictController {

    private final DistrictService service;

    @Operation(description = "Find districts by region id", tags = {"Places"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Region found"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Region not found"
            )
    })
    @GetMapping(value = "/region/{regionId}/district", produces = MediaType.APPLICATION_JSON_VALUE)
    public ListOfObjects<DistrictDTO> getDistrictsByRegionId(@PathVariable Long regionId) {
        var districts = service.findAllDistrictsByRegion(regionId);
        districts.forEach(district ->
                district.add(linkTo(methodOn(DistrictController.class).getDistrictById(district.getId())).withSelfRel())
        );
        var result = new ListOfObjects<>(districts);
        result.add(linkTo(methodOn(DistrictController.class).getDistrictsByRegionId(regionId)).withSelfRel());
        return result;
    }

    @Operation(description = "Find district by id", tags = {"Places"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "district found"),
            @ApiResponse(
                    responseCode = "404",
                    description = "district not found"
            )
    })
    @GetMapping(value = "/district/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SingleObject<DistrictDTO> getDistrictById(@PathVariable Long id) {
        var result = service.findDistrictById(id)
                .map(SingleObject::new)
                .orElseThrow(() -> new DistrictNotFoundStaffException(id));
        result.add(linkTo(methodOn(DistrictController.class).getDistrictById(id)).withSelfRel());
        return result;
    }

}
