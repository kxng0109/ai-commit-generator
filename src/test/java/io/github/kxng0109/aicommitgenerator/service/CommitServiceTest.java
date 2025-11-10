package io.github.kxng0109.aicommitgenerator.service;

import io.github.kxng0109.aicommitgenerator.model.dto.CommitMessageRequest;
import io.github.kxng0109.aicommitgenerator.model.dto.CommitMessageResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.client.ChatClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommitServiceTest {
    @Mock
    private ChatClient chatClient;

    @InjectMocks
    private CommitService commitService;

    @Test
    public void generateMessage_withValidDiff_should_returnAMessage() {
        CommitMessageRequest commitMessageRequest = new CommitMessageRequest("a commit message");
        CommitMessageResponse commitMessageResponse = new CommitMessageResponse("feat(test): this is a mock commit");

        ChatClient.ChatClientRequestSpec requestSpec = mock(ChatClient.ChatClientRequestSpec.class);
        ChatClient.CallResponseSpec callSpec = mock(ChatClient.CallResponseSpec.class);

        when(chatClient.prompt()).thenReturn(requestSpec);
        when(requestSpec.system(anyString())).thenReturn(requestSpec);
        when(requestSpec.user(anyString())).thenReturn(requestSpec);
        when(requestSpec.call()).thenReturn(callSpec);
        when(callSpec.content()).thenReturn(commitMessageResponse.commitMessage());


        String response = commitService.generateMessage(commitMessageRequest.diff());

        assertNotNull(response);
        assertEquals(commitMessageResponse.commitMessage(), response);
    }
}
