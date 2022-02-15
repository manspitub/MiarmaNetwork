package com.salesianos.dam.anuel.MiarmaNetwork.model;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;
import java.time.Instant;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class Publicacion {

    @Id
    @GeneratedValue
    private Long id;

    private String titulo;

    @CreatedDate
    private Instant createAt;

    @CreatedDate
    private Instant updatedAt;

    @CreatedBy
    private User user;

    private String texto;

    @LastModifiedBy
    private String lastModifiedBy;

    @NonNull
    private String file;



}
