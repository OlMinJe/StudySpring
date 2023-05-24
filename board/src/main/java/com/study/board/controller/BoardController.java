package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * packageName    : com.study.board.controller
 * fileName       : BoardController
 * author           : minjelee
 * date           : 2023/05/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/24        minjelee        최초 생성
 */

@Controller
public class BoardController {

/* Test
    @GetMapping("/") // 해당 경로로 들어왔을 때
    @ResponseBody // 글자를 그래도 Body 부분에 넣어준다.
    public String main() {
        return "Hello word";
    }
 */
    // BoardService 클래스를 사용하기 위해
    @Autowired
    private BoardService boardService;

    /**
     * BoardWrite.html 연결
     * localhost:8080/board/write
     */
    @GetMapping("/board/write")
    public String boardWriteForm() {
        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board) {
        //System.out.println("title: " + board.getTitle() + " content: " + board.getContent());


        boardService.write(board);

        return "";
    }

    /**
     * 게시물 리스트 출력하기 - 컨트롤러에서 데이터를 담아서 화면에서 보여지는 부분에 전송하기
     * boardlist.html
     */
    @GetMapping("/board/list")
    public String boardList(Model model) { // 이때 데이터를 담아서 보내주는게 Model
        model.addAttribute("list", boardService.boardList());
            // addAttribute로 추가해주기 (list라는 이름으로 boardService.boardList()을 보내기)
        return "boardlist";
    }

    /**
     * 상세 페이지
     * boardview.html
     */
    @GetMapping("/board/view")
    // 만약 localhost:8080/board/view?id=1이면 id에 1이 들어감
    public String boardView(Model model, Integer id) {
        model.addAttribute("article", boardService.boardView(id));
        return "boardview";
    }
}
