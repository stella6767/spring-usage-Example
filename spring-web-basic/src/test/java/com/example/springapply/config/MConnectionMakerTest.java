package com.example.springapply.config;

import com.example.springapply.repository.board.BoardRepository;
import com.example.springapply.repository.board.JdbcBoardRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class MConnectionMakerTest {


    BoardRepository boardRepository = (BoardRepository) new JdbcBoardRepositoryImpl(new MConnectionMaker());
    PlatformTransactionManager txManager = new DataSourceTransactionManager();


    @Test
    void transctonalTest(){


//        TransactionDefinition def = new DefaultTransactionDefinition();
//        TransactionStatus status = txManager.getTransaction(def);
//
//        try {
//
//            throw new RuntimeException("쿼리 에러");
//
//            this.txManager.commit(status);
//
//        }catch (Exception e){
//
//            txManager.rollback(status);
//
//        }








    }


    @Test
    void jdbcTest() {

        String driverName="com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/servlet?serverTimezone=Asia/Seoul";
        String id = "servlet"; // MySQL ID
        String pwd ="Kang@1234";     // MYSQL Password

        try{
            //[1] JDBC 드라이버 로드
            Class.forName(driverName);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            return;
        }
        System.out.println("mysql jdbc Driver registered!!");

        //[2]데이타베이스 연결
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,id,pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("DB연결성공!!");

        //[3]데이타베이스 연결 해제
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}