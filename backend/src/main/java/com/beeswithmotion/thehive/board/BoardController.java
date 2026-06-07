package com.beeswithmotion.thehive.board;

import com.beeswithmotion.thehive.thread.ThreadService;
import com.beeswithmotion.thehive.thread.Thread;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;
    private final ThreadService threadService;

    public BoardController(BoardService boardService, ThreadService threadService) {
        this.boardService = boardService;
        this.threadService = threadService;
    }

    @GetMapping()
    public ResponseEntity<List<Board>> getBoards() {return boardService.getBoards();}

    @GetMapping("/{boardAbbreviation}")
    public ResponseEntity<Board> getBoardByAbbreviation(@PathVariable String boardAbbreviation) {
        return boardService.getBoardByAbbreviation(boardAbbreviation);
    }

    @GetMapping("/{boardId}/threads")
    public ResponseEntity<List<Thread>> getThreads(@PathVariable Long boardId) {
        return threadService.getThreadsByBoardId(boardId);
    }

    @GetMapping("/{boardId}/threads/{threadId}")
    public ResponseEntity<Thread> getThread(@PathVariable Long boardId, @PathVariable Long threadId) {
        return threadService.getThreadByBoardIdAndThreadId(boardId, threadId);
    }
}
