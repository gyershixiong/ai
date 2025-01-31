package com.ershixiong.ai.api.controller;

import com.ershixiong.ai.application.converter.CityConverter;
import com.ershixiong.ai.application.service.CityApplicationService;
import com.ershixiong.ai.domain.model.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CityApplicationService cityApplicationService;

    @Autowired
    private CityConverter cityConverter;

    @Test
    public void getCityById_ValidId_ReturnsCityDTO() throws Exception {
        // 准备
        Long cityId = 1L;
        City city = new City(cityId, "CityName", "CountryCode", "District", 100000);

        when(cityApplicationService.getById(cityId)).thenReturn(city);

        mockMvc.perform(get("/api/cities/{id}", cityId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(cityId))
                .andExpect(jsonPath("$.data.name").value("CityName"))
                .andExpect(jsonPath("$.data.countrycode").value("CountryCode"))
                .andExpect(jsonPath("$.data.district").value("District"))
                .andExpect(jsonPath("$.data.population").value(100000));
    }

    @Test
    public void getCityById_InvalidId_ReturnsNull() throws Exception {
        // 准备
        Long cityId = 999L;

        when(cityApplicationService.getById(cityId)).thenReturn(null);

        mockMvc.perform(get("/api/cities/{id}", cityId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isEmpty());
    }
}
