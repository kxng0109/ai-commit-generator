package io.github.kxng0109.aicommitgenerator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.kxng0109.aicommitgenerator.model.dto.CommitMessageRequest;
import io.github.kxng0109.aicommitgenerator.service.CommitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommitController.class)
public class CommitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CommitService commitService;

    @Test
    public void generateCommit_should_return200Ok_when_validDiff() throws Exception {
        CommitMessageRequest request = new CommitMessageRequest("some changes");

        when(commitService.generateMessage(any(String.class)))
                .thenReturn("feat(test): this is a mock commit");

        mockMvc.perform(post("/api/v1/generate-commit")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.commitMessage").isNotEmpty());
    }

    @Test
    public void generateCommit_should_throw400_when_invalidOrEmptyDiff() throws Exception {
        CommitMessageRequest request = new CommitMessageRequest("");

        mockMvc.perform(post("/api/v1/generate-commit")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isBadRequest());
    }
}
