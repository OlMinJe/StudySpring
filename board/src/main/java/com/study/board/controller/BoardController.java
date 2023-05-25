package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
    public String boardWritePro(Board board, Model model,// Model은 message 박스를 위해 추가함
                                MultipartFile file) throws Exception {  // file 업로드를 위해 추가함
        //System.out.println("title: " + board.getTitle() + " content: " + board.getContent());

        // 05.25 파일 업로드를 위해 file도 추가해준다.
        boardService.write(board, file);

        System.out.println(board);

        // message 박스 출력을 위한 코드
        if (board.getTitle() != "" && board.getContent() != "") {
            model.addAttribute("message", "글 작성이 완료되었습니다.");
        } else {
            model.addAttribute("message", "글 작성이 실패하였습니다.");
        }

        model.addAttribute("searchUrl", "/board/list");

        return "message";
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

    /**
     * 특정 게시글 삭제 구현
     */
    @GetMapping("/board/delete")
    public String boardDelete(Integer id) {
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    /**
     * 특정 게시물 수정 구현 - 수정 페이지로 들어가기
     */
    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model) {
            // @PathVariable을 사용하면 localhost:8080/board/modifiy/선택한id번호 형식으로 들어가지게 해준다.

        // 수정할 때 해당 글의 정보를 불러오기 - boardmodify.html에서 value로 불러올 수 있음
        model.addAttribute("board", boardService.boardView(id));

        return "boardmodify";
    }

    /**
     * 특정 게시물 수정 구현 - 업데이트 하기
     */
    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, // 위의 {id}를 담아와야 함.
                              Board board,  // wirte랑 똑같이 안에 있는 내용을 받아오면 됨.
                              MultipartFile file) throws Exception {  // 파일 업로드를 위해 수정해주기
        Board boardTemp = boardService.boardView(id); // 기존에 있던 글이 담겨져옴 -> 여기에 바뀐 값을 세팅할 예정
        boardTemp.setTitle(board.getTitle()); // 수정한 값을 세팅해주기
        boardTemp.setContent(board.getContent());

        // 여기에서 작성으로 보내주기
        boardService.write(boardTemp, file);

        return "redirect:/board/list";
    }
}