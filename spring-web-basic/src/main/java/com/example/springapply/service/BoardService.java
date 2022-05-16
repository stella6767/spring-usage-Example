package com.example.springapply.service;


import com.example.springapply.domain.board.Board;
import com.example.springapply.domain.board.dto.BoardReqDto;
import com.example.springapply.repository.board.BoardRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.SQLException;
import java.util.List;

public class BoardService {

    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void save(String title, String content) throws SQLException, ClassNotFoundException {

        BoardReqDto dto = BoardReqDto.builder()
                .title(title)
                .content(content)
                .build();


        boardRepository.save(new Board());
    }


    /**
     * 내 멋대로 자바, 자바 jdk, jre(Java Runtime Environment), jvm
     *
     *javac : 자바 컴파일러. 자바 소스파일을 바이트 코드로 변환
     * java : javac가 만든 클래스 파일을 해석 및 실행
     * jar : 서로 관련있는 클래스 라이브러리들과 리소스를 하나의 파일로 묵어주는 툴
     * jdb : 자바 디버깅 툴
     *
     *
     * JDK에 있는 javac프로그램이 .java파일을 .class 바이너리 코드로 컴파일, 파일 생성
     * ‘java’가 JRE를 통해 JVM을 실행하고 .class파일을 인수로 보냄.(?)(정확하지않음)
     * JVM속에 있는 class loader가 작동해 클래스 로더가 실행에 필요한 모든 파일들을 찾는다.(.class)
     * Byte code verifier가 위에서 실행된 class loader가 올린 파일들이 맞는지 검사한다. 즉 자바 프로그램은 실행 시점에 코드의 유효성을 검증하는 과정을 거쳐 보안에 강하다는 장점이 있다.
     * JVM속에서 자바 인터프리터나 JIT(Just-In-Time)컴파일러를 통해 바이트코드를 다시 헌 번 기계어로 인터프리터를 통해서 해석된다.
     */



    @Transactional
    public List<Board> findAll() throws SQLException, ClassNotFoundException {

        System.out.println("transaction open? " + TransactionSynchronizationManager.isActualTransactionActive());

        return boardRepository.findAll();
    }
}
