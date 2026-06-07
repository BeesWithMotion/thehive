package com.beeswithmotion.thehive.board;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public ResponseEntity<List<Board>> getBoards() {
        List<Board> boards = boardRepository.findAll();
        return ResponseEntity.ok(boards);
    }

    public ResponseEntity<Board> getBoardByAbbreviation(String boardAbbreviation) {
        Board board = boardRepository.findByBoardAbbreviation(boardAbbreviation);
        return ResponseEntity.ok(board);
    }
}
