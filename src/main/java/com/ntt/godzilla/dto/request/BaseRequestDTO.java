package com.ntt.godzilla.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaseRequestDTO {
    private int page;

    private int offset;
}
