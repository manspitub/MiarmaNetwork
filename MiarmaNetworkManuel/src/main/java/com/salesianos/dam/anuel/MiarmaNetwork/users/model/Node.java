package com.salesianos.dam.anuel.MiarmaNetwork.users.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Node {

    long out;
    long in;

}
