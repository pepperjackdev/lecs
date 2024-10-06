package me.pepperjackdev.lecs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Subject {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
}
