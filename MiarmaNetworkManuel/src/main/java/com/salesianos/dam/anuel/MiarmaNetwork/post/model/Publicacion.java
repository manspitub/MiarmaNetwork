package com.salesianos.dam.anuel.MiarmaNetwork.post.model;

import com.salesianos.dam.anuel.MiarmaNetwork.users.model.User;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class Publicacion {

    @Id
    @GeneratedValue
    private Long id;

    private String titulo;

    @CreatedDate
    private Instant createAt;



    @ManyToOne
    @JoinColumn(name = "user_id")
    @CreatedBy
    private User user;

    private String texto;



    @NonNull
    private String file;

    @NotBlank
    private boolean isPublic;






}
