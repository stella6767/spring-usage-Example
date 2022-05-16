package com.example.springapply.config;


import com.example.springapply.repository.board.BoardRepository;
import com.example.springapply.repository.board.JdbcTemplateBoardRepositoryImpl;
import com.example.springapply.service.BoardService;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DBConfig {

    String driverName="com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/servlet?serverTimezone=Asia/Seoul";
    String id = "servlet"; // MySQL ID
    String pwd ="Kang@1234";

    @Bean
    public DataSource dataSource(){

        return DataSourceBuilder
                .create()
                .url(url)
                .driverClassName(driverName)
                .password(pwd)
                .username(id)
                .build();
    }


    @Bean
    public BoardRepository boardRepository(){

        return new JdbcTemplateBoardRepositoryImpl(dataSource());
    }


    @Bean
    public ConnectionMaker connectionMaker(){
        return new MConnectionMaker();
    }


    @Bean
    public PlatformTransactionManager txManager(){

        /*여러 과정을 하나의 행위로 묶을 때 사용된다.
           하나라도 실패하면 모두 취소되어야 한다. 이렇게 함으로써 데이터의 무결성을 보장

           스프링은 선언적 트랜잭션 지원.
           PlatformTransactionManager 라는 인터페이스를 이용해 추상화시켰다.

           JDBC의 경우, DataSourceTransactionManager를 관리자로 등록합니다.
           JPA는 JpaTransactionManager
            */



        return new DataSourceTransactionManager(dataSource());
    }


    @Bean
    public BoardService boardService(){

        return new BoardService(boardRepository());
    }


}
