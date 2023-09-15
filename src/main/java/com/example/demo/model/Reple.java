package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "reples")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
