package com.salesianos.dam.anuel.MiarmaNetwork.users.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
