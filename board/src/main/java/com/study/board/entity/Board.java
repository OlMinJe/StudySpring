package com.study.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * packageName    : com.study.board.entity
 * fileName       : Board
 * author         : minjelee
 * date           : 2023/05/24
 * description    : 테이블 명과 일치하게 만들어줌.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/24        minjelee       최초 생성
 */

@Entity // 이 어노테이션은 이 클래스가 DB에 있는 테이블이라는 것을 의미한다.
@Data // 컨트롤러에서 필드를 사용할 수 있게끔 만들어줌.
public class Board {
    // filed 지정
    @Id // primary key를 의미한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    private String filename;

    private String filepath;
}
