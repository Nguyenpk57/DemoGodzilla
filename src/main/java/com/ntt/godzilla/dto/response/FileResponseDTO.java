package com.ntt.godzilla.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileResponseDTO {
    private Long productId;
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}