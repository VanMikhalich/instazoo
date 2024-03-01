package ru.ivan.instazoo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
public class CommentDTO {
    private Long id;

    @NotBlank(message = "Field text is required")
    @Size(max = 400, message = "Comment must been less than 400 symbols")
    private String text;

    @JsonFormat(pattern = "hh:mm:ss dd.MM.yyyy")
    private LocalDateTime creationTime;

    private String author;
    private Long postId;
}
