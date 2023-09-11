package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;
    private int userSeq;
    private String title;
    private String content;

    @OneToMany(mappedBy = "postSeq")
    private List<Reple> reples;
}
