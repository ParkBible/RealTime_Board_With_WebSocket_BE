package com.example.demo.model;

import jakarta.persistence.*;

@Entity(name = "reples")
public class Reple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;
    private String content;
    private int postSeq;

    @OneToOne
    @JoinColumn(name = "userSeq")
    private User user;
}
