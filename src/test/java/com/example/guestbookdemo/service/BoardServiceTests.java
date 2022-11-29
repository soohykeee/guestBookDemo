package com.example.guestbookdemo.service;

import com.example.guestbookdemo.dto.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {
        BoardDTO dto = BoardDTO.builder()
                .title("Test - Title")
                .content("Test... Content")
                .writerEmail("user55@aaa.com")  //DB에 존재하는 email 값으로 해야 에러가 나지 않음
                .build();

        Long bno = boardService.register(dto);
    }

    @Test
    public void testGet() {
        Long bno = 100L;
        BoardDTO boardDTO = boardService.get(100L);
        System.out.println(boardDTO);
    }

    @Test
    public void testRemove() {
        Long bno = 4L;
        boardService.removeWithReplies(bno);
    }

    @Test
    public void testModify() {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(2L)
                .title("제목 변경테스트")
                .content("내용 변경테스트")
                .build();

        boardService.modify(boardDTO);
    }

}
