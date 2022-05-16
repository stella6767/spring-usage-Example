package com.example.springapply.web;

import com.example.springapply.domain.board.dto.BoardReqDto;
import com.example.springapply.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Date;


/**
 *@ResponseBody 어노테이션을 명시하면 BufferedWriter 작동
 *
 * @RequestBody 어노테이션을 명시하면 BufferedReader 작동
 */


//@Component
//@RestController  //나는 요청을 받고 라우팅을 해주고 다시 데이터를 리턴해주는 역할의 빈이야.
//@Service
//@Controller
//@Repository
@Slf4j
@Controller
public class BoardController {


    /**
     * 스프링 프레임워크는 TaskExecutor 인터페이스와 TaskScheduler 인터페이스로 태스크의 비동기 시행과 스케줄링에 대한 추상화를 각각 제공한다.
     * 스프링의 TaskExecutor 인터페이스는 java.util.concurrent.Executor 인터페이스와 같다.
     */

    private final TaskExecutor taskExecutor;
    private final BoardService boardService;


    //@Autowired  생성자가 하나면 생략가능
    public BoardController(TaskExecutor taskExecutor, BoardService boardService) {
        this.taskExecutor = taskExecutor;
        this.boardService = boardService;
    }


    @RequestMapping("/detail")
    public String detail(){

        System.out.println("게시글 등록 페이지로 이동");

        return "board/detail";
    }


    @RequestMapping("/save")
    public String save(BoardReqDto dto){

        System.out.println("게시글 등록 " +  dto);

        return "board/list";
    }



    @RequestMapping("/findAll")
    public String findAll(HttpServletRequest req, Model model) throws SQLException, ClassNotFoundException {

        System.out.println("게시글 리스트");

        model.addAttribute("date", new Date());

        System.out.println("transaction open? " + TransactionSynchronizationManager.isActualTransactionActive());
        model.addAttribute("boards",
                boardService.findAll());
        System.out.println("transaction open? " + TransactionSynchronizationManager.isActualTransactionActive());

        return "board/list";
    }



}
