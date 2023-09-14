package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    @ManyToOne
    @JoinColumn(name = "userSeq")
    private User user;
    private String title;
    private String content;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "postSeq")
    private List<Reple> reples;
}
