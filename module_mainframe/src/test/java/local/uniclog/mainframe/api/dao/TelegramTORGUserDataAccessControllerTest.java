package local.uniclog.mainframe.api.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import local.uniclog.mainframe.dao.telegram.dto.TelegramTORGUserEntityDto;
import local.uniclog.mainframe.dao.telegram.service.TelegramTORGUserEntityDataAccessService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for TelegramTORGUserDataAccessController
 */
@WebMvcTest(controllers = TelegramTORGUserDataAccessController.class)
class TelegramTORGUserDataAccessControllerTest {

    private static final String api = "/api/TelegramTORGUserDataAccessController";
    private final Long userTelegramId = 1L;
    @MockBean
    private TelegramTORGUserEntityDataAccessService service;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    private TelegramTORGUserEntityDto entity;

    @BeforeEach
    void setUp() {
        entity = TelegramTORGUserEntityDto.builder()
                .id(1L)
                .userTelegramId(userTelegramId)
                .subscriber(true)
                .build();
    }

    @Test
    void save() throws Exception {
        when(service.save(any())).thenReturn(entity);
        mockMvc.perform(MockMvcRequestBuilders
                        .put(api + "/save")
                        .content(objectMapper.writeValueAsString(entity))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(entity)));

        verify(service).save(entity);
        verifyNoMoreInteractions(service);
    }

    @Test
    void saveFailedTest1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put(api + "/save"))
                .andExpect(status().isBadRequest());
        mockMvc.perform(MockMvcRequestBuilders
                        .put(api + "/save")
                        .content(objectMapper.writeValueAsString("null"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(service);
    }

    @Test
    void saveFailedTest2() throws Exception {
        when(service.save(any())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders
                        .put(api + "/save")
                        .content(objectMapper.writeValueAsString(entity))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

        verify(service).save(entity);
        verifyNoMoreInteractions(service);
    }

    @Test
    void update() throws Exception {
        when(service.update(any())).thenReturn(entity);
        mockMvc.perform(MockMvcRequestBuilders
                        .patch(api + "/update")
                        .content(objectMapper.writeValueAsString(entity))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(entity)));

        verify(service).update(entity);
        verifyNoMoreInteractions(service);
    }

    @Test
    void updateFailedTest() throws Exception {
        when(service.update(any())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders
                        .patch(api + "/update")
                        .content(objectMapper.writeValueAsString(entity))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

        verify(service).update(entity);
        verifyNoMoreInteractions(service);
    }

    @Test
    void findByUserTelegramId() throws Exception {
        when(service.findByUserTelegramId(any())).thenReturn(entity);
        mockMvc.perform(MockMvcRequestBuilders
                        .get(api + "/findByUserTelegramId/" + userTelegramId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(entity)));

        verify(service).findByUserTelegramId(userTelegramId);
        verifyNoMoreInteractions(service);
    }

    @Test
    void findByUserTelegramIdFailedTest() throws Exception {
        when(service.findByUserTelegramId(anyLong())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders
                        .get(api + "/findByUserTelegramId/" + userTelegramId))
                .andExpect(status().isNotFound());

        verify(service).findByUserTelegramId(userTelegramId);
        verifyNoMoreInteractions(service);
    }

    @Test
    void findAllSubscribers() throws Exception {
        when(service.findAllSubscribers()).thenReturn(singletonList(entity));
        mockMvc.perform(MockMvcRequestBuilders
                        .get(api + "/findAllSubscribers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(singletonList(entity))));

        verify(service).findAllSubscribers();
        verifyNoMoreInteractions(service);
    }

    @Test
    void findAllSubscribersFailedTest() throws Exception {
        when(service.findAllSubscribers()).thenReturn(emptyList());
        mockMvc.perform(MockMvcRequestBuilders
                        .get(api + "/findAllSubscribers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service).findAllSubscribers();
        verifyNoMoreInteractions(service);
    }

    @Test
    void findAll() throws Exception {
        when(service.findAll()).thenReturn(singletonList(entity));
        mockMvc.perform(MockMvcRequestBuilders
                        .get(api + "/findAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(singletonList(entity))));

        verify(service).findAll();
        verifyNoMoreInteractions(service);
    }

    @Test
    void findAllFailedTest() throws Exception {
        when(service.findAll()).thenReturn(emptyList());
        mockMvc.perform(MockMvcRequestBuilders
                        .get(api + "/findAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service).findAll();
        verifyNoMoreInteractions(service);
    }

    @Test
    void deleteAllByUserTelegramId() throws Exception {
        when(service.deleteAllByUserTelegramId(anyLong())).thenReturn(emptyList());
        mockMvc.perform(MockMvcRequestBuilders
                        .delete(api + "/deleteAllByUserTelegramId/" + userTelegramId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

        verify(service).deleteAllByUserTelegramId(userTelegramId);
        verifyNoMoreInteractions(service);
    }

    @Test
    void deleteAllByUserTelegramIdFailedTest() throws Exception {
        when(service.deleteAllByUserTelegramId(anyLong())).thenReturn(emptyList());
        mockMvc.perform(MockMvcRequestBuilders
                        .delete(api + "/deleteAllByUserTelegramId/" + userTelegramId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

        verify(service).deleteAllByUserTelegramId(userTelegramId);
        verifyNoMoreInteractions(service);
    }
}