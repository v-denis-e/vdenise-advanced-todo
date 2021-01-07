package kz.vdenise.vdeniseadvancedtodo.staff.place.controllers.v1;

import kz.vdenise.vdeniseadvancedtodo.controller.TodoControllerAdvice;
import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.DistrictDTO;
import kz.vdenise.vdeniseadvancedtodo.staff.place.services.DistrictService;
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

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DistrictControllerTest {

    @Mock
    DistrictService service;
    @InjectMocks
    DistrictController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new TodoControllerAdvice()).build();
    }

    @Test
    void findDistrictsByRegionId() throws Exception {
        var regionId = 2L;
        var district = new DistrictDTO(1L, "district123123");
        given(service.findAllDistrictsByRegion(regionId))
                .willReturn(Set.of(district));

        mockMvc.perform(get("/api/v1/staff/place/region/" + regionId + "/district"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("district123123")));
        then(service).should().findAllDistrictsByRegion(regionId);
    }

    @Test
    void findDistrictById() throws Exception {
        var district = new DistrictDTO(1L, "district743291874921");
        given(service.findDistrictById(district.getId()))
                .willReturn(Optional.of(district));

        mockMvc.perform(get("/api/v1/staff/place/district/" + district.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("district743291874921")));
    }

    @Test
    void findDistrictById_districtDoesNotExist() throws Exception {
        var district = 1L;
        given(service.findDistrictById(district))
                .willReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/staff/place/district/" + district))
                .andExpect(status().isNotFound());
    }
}