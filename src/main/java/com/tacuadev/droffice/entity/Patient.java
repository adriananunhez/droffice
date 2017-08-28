package com.tacuadev.droffice.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fd on 23/08/17.
 */
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "Bigserial")
    public Long id;

    @Column(name = "opening_date", nullable = false)
    public Date openingDate;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "document_number")
    public String documentNumber;

    @Column(name = "sex_int", nullable = false)
    public Integer sexInt;

    @Column(name = "sex", nullable = false)
    public String sex;

    @Column(name = "birthday", nullable = false)
    public Date birthday;

    @Column(name = "address",nullable = false)
    public String address;

    @Column(name = "creation_date", nullable = false)
    public Date creationDate;

}
