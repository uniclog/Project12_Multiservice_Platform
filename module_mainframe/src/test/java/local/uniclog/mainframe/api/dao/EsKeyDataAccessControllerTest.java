package local.uniclog.mainframe.api.dao;


import com.fasterxml.jackson.databind.ObjectMapper;
import local.uniclog.mainframe.dao.extensions.service_ekey.entity.EsKeyEntity;
import local.uniclog.mainframe.dao.extensions.service_ekey.service.EsKeyEntityDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static java.util.List.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for EsKeyDataAccessController
 */
@WebMvcTest
class EsKeyDataAccessControllerTest {
    @MockBean
    private EsKeyEntityDataService service;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    private EsKeyEntity entity;
    private String key;
    private LocalDateTime date;

    @BeforeEach
    void setUp() {
        entity = new EsKeyEntity();
        key = "1234-1234-1234-1234-1234";
        entity.setKeyValue(key);
        date = entity.getDate();
    }

    @Test
    void save() throws Exception {
        when(service.save(any())).thenReturn(entity);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/save")
                        .content(objectMapper.writeValueAsString(entity))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(entity)));

        verify(service).save(entity);
        verifyNoMoreInteractions(service);
    }

    @Test
    void save_failed_1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/save"))
                .andExpect(status().isBadRequest());
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/save")
                        .content(objectMapper.writeValueAsString("null"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(service);
    }

    @Test
    void save_failed_2() throws Exception {
        when(service.save(any())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/save")
                        .content(objectMapper.writeValueAsString(entity))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

        verify(service).save(entity);
        verifyNoMoreInteractions(service);
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/delete")
                        .content(objectMapper.writeValueAsString(entity))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service).delete(entity);
        verifyNoMoreInteractions(service);
    }

    @Test
    void deleteAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/deleteAll"))
                .andExpect(status().isOk());

        verify(service).deleteAll();
        verifyNoMoreInteractions(service);
    }

    @Test
    void deleteByKey() throws Exception {
        when(service.deleteByKey(anyString())).thenReturn(entity);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/deleteByKey/" + key))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(entity)));

        verify(service).deleteByKey(key);
        verifyNoMoreInteractions(service);
    }

    @Test
    void deleteByKey_Failed_1() throws Exception {
        when(service.deleteByKey(anyString())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/deleteByKey/" + key))
                .andExpect(status().isNotFound());

        verify(service).deleteByKey(key);
        verifyNoMoreInteractions(service);
    }

    @Test
    void findByDateAfter() throws Exception {
        var list = of(entity);
        when(service.findByDateAfter(any())).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/findByDateAfter/")
                        .content(objectMapper.writeValueAsString(date))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(list)));

        verify(service).findByDateAfter(date);
        verifyNoMoreInteractions(service);
    }

    @Test
    void findByDateAfter_Failed_1() throws Exception {
        when(service.findByDateAfter(any())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/findByDateAfter/")
                        .content(objectMapper.writeValueAsString(date))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service).findByDateAfter(date);
        verifyNoMoreInteractions(service);
    }

    @Test
    void findAll() throws Exception {
        var list = of(entity);
        when(service.findAll()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/findAll/"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(list)));

        verify(service).findAll();
        verifyNoMoreInteractions(service);
    }

    @Test
    void findAll_Failed_1() throws Exception {
        when(service.findAll()).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/findAll/"))
                .andExpect(status().isNotFound());

        verify(service).findAll();
        verifyNoMoreInteractions(service);
    }

    @Test
    void findByKey() throws Exception {
        when(service.findByKey(anyString())).thenReturn(entity);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/findByKey/" + key))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(entity)));

        verify(service).findByKey(key);
        verifyNoMoreInteractions(service);
    }

    @Test
    void findByKey_Failed_1() throws Exception {
        when(service.findByKey(anyString())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/findByKey/" + key))
                .andExpect(status().isNotFound());

        verify(service).findByKey(key);
        verifyNoMoreInteractions(service);
    }
}