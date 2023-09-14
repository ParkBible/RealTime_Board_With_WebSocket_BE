package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "reples")
@Data
public class Reple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;
    private String content;
    private int postSeq;

    @ManyToOne
    @JoinColumn(name = "userSeq")
    private User user;
}
