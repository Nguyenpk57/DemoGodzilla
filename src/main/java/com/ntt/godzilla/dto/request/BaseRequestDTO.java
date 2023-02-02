package com.ntt.godzilla.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class BaseRequestDTO {
    private int page;

    private int offset;
}
