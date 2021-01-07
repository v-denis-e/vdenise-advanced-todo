package kz.vdenise.vdeniseadvancedtodo.staff.place.controllers.v1;

import kz.vdenise.vdeniseadvancedtodo.controller.TodoControllerAdvice;
import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.CountryDTO;
import kz.vdenise.vdeniseadvancedtodo.staff.place.model.v1.CountryWithRegionDTO;
import kz.vdenise.vdeniseadvancedtodo.staff.place.services.CountryService;
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
class CountryControllerTest {

    @Mock
    CountryService service;
    @InjectMocks
    CountryController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(TodoControllerAdvice.class).build();
    }

    @Test
    void findCountries() throws Exception {
        var id = 1L;
        var name = "country";
        var source = new CountryDTO(id, name);
        given(service.findAllCountries())
                .willReturn(Set.of(source));

        mockMvc.perform(get("/api/v1/staff/place"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(name)));
        then(service).should().findAllCountries();
    }

    @Test
    void findCountryById() throws Exception {
        var source = new CountryWithRegionDTO(1L, "name123321123321");
        given(service.findCountryById(source.getId()))
                .willReturn(Optional.of(source));

        mockMvc.perform(get("/api/v1/staff/place/" + source.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(source.getName())));
        then(service).should().findCountryById(source.getId());
    }

    @Test
    void findByCountryId_countryDoesNotExist() throws Exception {
        var id = 1L;
        given(service.findCountryById(id))
                .willReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/staff/place/" + id))
                .andExpect(status().isNotFound());
    }
}