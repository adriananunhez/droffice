package com.tacuadev.droffice.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fd on 23/08/17.
 */

@Entity
@Table(name = "patient_medical_history")
public class PatientMedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "Bigserial")
    public Long id;

    @Column(name = "date", nullable = false)
    public Date date;

    @Column(name = "row_number", nullable = false)
    public Integer rowNumber;

    @Column(name = "symptom", columnDefinition = "text", nullable = false)
    public String symptom;

    @Column(name = "diagnostic", nullable = false)
    public String diagnostic;

    @Column(name = "prescription", nullable = false)
    public String prescription;

    @Column(name = "creation_date", nullable = false)
    public Date creationDate;

    @ManyToOne()
    @JoinColumn(name = "patient_id", nullable = false, referencedColumnName = "id", columnDefinition = "Bigint")
    public Patient patient;

    @ManyToOne()
    @JoinColumn(name = "place_attention_id", nullable = false, referencedColumnName = "id", columnDefinition = "Bigint")
    public PlaceAttention placeAttention;
}
