package com.beeswithmotion.thehive.board;

import jakarta.persistence.*;

@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private String boardName;
    private String boardDescription;
}
