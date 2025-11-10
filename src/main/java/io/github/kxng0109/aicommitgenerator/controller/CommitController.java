package io.github.kxng0109.aicommitgenerator.controller;

import io.github.kxng0109.aicommitgenerator.model.dto.CommitMessageRequest;
import io.github.kxng0109.aicommitgenerator.model.dto.CommitMessageResponse;
import io.github.kxng0109.aicommitgenerator.service.CommitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class CommitController {

    private final CommitService commitService;

    @PostMapping("/generate-commit")
    public ResponseEntity<CommitMessageResponse> generateCommit(
            @Valid @RequestBody CommitMessageRequest commitMessageRequest
    ) {
        log.info("Received commit message request: {}", commitMessageRequest.diff());

        try {
            String commitMessage = commitService.generateMessage(commitMessageRequest.diff());
            return ResponseEntity.ok(new CommitMessageResponse(commitMessage));
        } catch (Exception e) {
            log.error("An error occurred: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
