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
}
