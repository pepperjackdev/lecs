package me.pepperjackdev.lecs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;

@Entity
@ToString(onlyExplicitlyIncluded = true)
public class Subject {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @ToString.Include
    private long id;

    @Column(nullable = false, unique = true)
    @Getter
    @ToString.Include
    private String title;

}
