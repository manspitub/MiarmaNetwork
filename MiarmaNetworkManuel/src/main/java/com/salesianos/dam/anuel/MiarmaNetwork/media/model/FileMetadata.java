package com.salesianos.dam.anuel.MiarmaNetwork.media.model;


import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import java.time.Instant;

@Data
@RequiredArgsConstructor
@Builder
public class FileMetadata {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @CreatedBy
    private String username;
    @CreatedDate
    private Instant createdAt;

    @NonNull
    private String uri;
    @NonNull
    private String type;

    private long size;

}
