package com.tacuadev.droffice.entity;

import javax.persistence.*;

/**
 * Created by fd on 23/08/17.
 */

@Entity
@Table(name = "place_attention")
public class PlaceAttention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true,nullable = false,columnDefinition = "Bigserial")
    public Long id;

    @Column(name = "description")
    public String description;
}
