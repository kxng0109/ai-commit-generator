package io.github.kxng0109.aicommitgenerator.model.dto;

import jakarta.validation.constraints.NotEmpty;

public record CommitMessageRequest(
        @NotEmpty(message = "Diff can not be empty")
        String diff
) {
}
