package com.salesianos.dam.anuel.MiarmaNetwork.users.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User received;


    private Boolean accepted;

    public Solicitud(User sender, User receiver) {
        this.sender = sender;
        this.received = receiver;
        this.accepted = false;
    }
}
