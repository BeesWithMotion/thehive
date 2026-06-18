package com.beeswithmotion.thehive.board;

import jakarta.persistence.*;

@Entity
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private String boardName;
    private String boardAbbreviation;
    private String boardDescription;

    public Board() {}

    public Long getBoardId() {
        return boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public String getBoardAbbreviation() {
        return boardAbbreviation;
    }

    public String getBoardDescription() {
        return boardDescription;
    }
}
