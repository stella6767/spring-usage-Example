package com.example.springapply.repository.board;

import com.example.springapply.domain.board.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

//DAO  순수하게 db에 접근해서 raw data를 처리용도
public class MemoryBoardRepositoryImpl implements BoardRepository {


    private static ConcurrentHashMap<Integer, Board> store = new ConcurrentHashMap<>();



    @Override
    public int save(Board board) {

        store.put(board.getId(), board);

        return 0;
    }

    @Override
    public List<Board> findAll() {

        ArrayList<Board> books = new ArrayList<>();

        for (Integer i :store.keySet()) {
            books.add(store.get(i));
        }

        return books;
    }

    @Override
    public Optional<Board> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }
}
