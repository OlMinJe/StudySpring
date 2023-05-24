package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.study.board.service
 * fileName       : BoardService
 * author         : minjelee
 * date           : 2023/05/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/24        minjelee       최초 생성
 */
@Service // 서비스를 컨트롤러에서 이용하면 된다.
public class BoardService {
    @Autowired // new 객체 생성 없이 알아서 불러와주는 어노테이션
    private BoardRepository boardRepository;

    /**
     * 글 작성 처리
     * @param board
     */
    public void write(Board board) {
        boardRepository.save(board); // save(엔티티) -> 전달해준다고 생각하면 됨
    }

    /**
     * 게시물 리스트 불러오기
     * @return boardRepository.findAll()
     */
    public List<Board> boardList() {
        return boardRepository.findAll(); // findeAll()은 list에 담긴 Board를 반환해준다.
    }

    /**
     * 특정 게시글 불러오기(상세보기)
     * @return
     */
    public Board boardView(Integer id) {
        Optional<Board> result = boardRepository.findById(id);
        return result.orElseGet(null);
        /* 위의 코드와 같은 의미
        return boardRepository.findById(id).get();
         */

    }
}
