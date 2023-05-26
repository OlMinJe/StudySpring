package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
     * 글 작성 처리 (05.25 수정 - 파일저장)
     * @param board
     */
    public void write(Board board, MultipartFile file) throws Exception { // MultipartFile => 글을 작성할 때 파일을 받아와줌 (file은 받아올 값을 저장할 빈껍데기)
        // 프로젝트 경로 받기
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";
        // 저장할 파일의 이름 - 식별자를 이용하여 생성하기
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename(); // 식별자 + 파일명
        // 파일을 생성할 때 projectPath라는 경로에 fileName 이라는 이름으로 파일을 저장
        File saveFile = new File(projectPath, fileName);
        // 저장이됨.
        file.transferTo(saveFile);

        // 그리고 board에 넣어주기
        board.setFilename(fileName);
        board.setFilepath("/files/" + fileName);
        boardRepository.save(board); // save(엔티티) -> 전달해준다고 생각하면 됨
    }

    /**
     * 게시물 리스트 불러오기
     * 페이징을 위한 값들 받아오기 5.26
     * @return boardRepository.findAll()
     */
    public Page<Board> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable); // findeAll()은 list에 담긴 Board를 반환해준다.
    }

    /**
     * 검색 기능 처리
     * @return
     */
    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {

        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }


    /**
     * 특정 게시글 불러오기(상세보기)
     * @return result.orElseGet(null);
     */
    public Board boardView(Integer id) {
        Optional<Board> result = boardRepository.findById(id);
        return result.orElseGet(null);
        /* 위의 코드와 같은 의미
        return boardRepository.findById(id).get();
         */

    }

    /**
     * 특젱 게시물 삭제하기
     * @return
     */
    public void boardDelete(Integer id) {
        boardRepository.deleteById(id);
    }
}
