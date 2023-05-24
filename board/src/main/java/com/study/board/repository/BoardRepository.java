package com.study.board.repository;

import com.study.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.study.board.repository
 * fileName       : BoardRepository
 * author         : minjelee
 * date           : 2023/05/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/24        minjelee       최초 생성
 */

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
            // JpaRepository란? 인터페이스이며, 엔티티에 있는 데이터를 조회하거나 저장과 변경을 할 수 있게 해줌
            // Board 클래스, primary key로 설정한 필드의 자료형

}
