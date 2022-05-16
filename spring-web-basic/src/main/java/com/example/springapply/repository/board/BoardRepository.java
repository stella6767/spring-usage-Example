package com.example.springapply.repository.board;

import com.example.springapply.domain.board.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    public int save(Board board);
    public List<Board> findAll();

    public Optional<Board> findById(int id);
}
