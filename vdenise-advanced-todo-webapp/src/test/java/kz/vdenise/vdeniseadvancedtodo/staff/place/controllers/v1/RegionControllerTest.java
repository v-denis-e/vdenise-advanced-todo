package kz.vdenise.vdeniseadvancedtodo.staff.place.controllers.v1;

import kz.vdenise.vdeniseadvancedtodo.controller.TodoControllerAdvice;
import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.RegionDTO;
import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.RegionWithDistrictDTO;
import kz.vdenise.vdeniseadvancedtodo.staff.place.services.RegionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class RegionControllerTest {

    @Mock
    RegionService service;
    @InjectMocks
    RegionController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new TodoControllerAdvice()).build();
    }

    @Test
    void findRegionsByCountryId() throws Exception {
        var countryId = 1L;
        var regions = Set.of(
                new RegionDTO(1L, "region1"),
                new RegionDTO(2L, "region2")
        );
        given(service.findAllRegionsByCountry(countryId))
                .willReturn(regions);

        mockMvc.perform(get("/api/v1/staff/place/" + countryId + "/region"))
                .andExpect(status().isOk())
                .andExpect(content().string(allOf(
                        containsString("region1"),
                        containsString("region2")
                )));
        then(service).should().findAllRegionsByCountry(countryId);
    }

    @Test
    void findRegionById() throws Exception {
        var region = new RegionWithDistrictDTO(1L, "region1");
        given(service.findRegionById(region.getId()))
                .willReturn(Optional.of(region));

        mockMvc.perform(get("/api/v1/staff/place/region/" + region.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("region1")));
        then(service).should().findRegionById(region.getId());
    }

    @Test
    void findRegionById_regionDoesNotExist() throws Exception {
        var regionId = 1L;
        given(service.findRegionById(regionId))
                .willReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/staff/place/region/" + regionId))
                .andExpect(status().isNotFound());
        then(service).should().findRegionById(regionId);
    }

}