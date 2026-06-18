package com.beeswithmotion.thehive.board;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public ResponseEntity<List<Board>> getBoards() {return boardService.getBoards();}

    @GetMapping("/{boardAbbreviation}")
    public ResponseEntity<Board> getBoardByAbbreviation(@PathVariable String boardAbbreviation) {
        return boardService.getBoardByAbbreviation(boardAbbreviation);
    }
}
