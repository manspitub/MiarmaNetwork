package com.salesianos.dam.anuel.MiarmaNetwork.users.model;

import lombok.Builder;
import org.neo4j.ogm.annotation.*;


@RelationshipEntity("IS_FOLLOWING")
@Builder
public class Follow {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private User seguidor;

    @EndNode
    private User seguido;




}
