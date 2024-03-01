package ru.ivan.instazoo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
public class PostDTO {
    private Long id;
    @NotBlank(message = "Field title is required")
    @Size(max = 50, message = "Field title must been less than 1000 symbols")
    private String title;

    @NotBlank(message = "Field description is required")
    @Size(max = 1000, message = "Field description must been less than 1000 symbols")
    private String description;

    @JsonFormat(pattern = "hh:mm:ss dd.MM.yyyy")
    private LocalDateTime creationTime;

    private String author;
}
