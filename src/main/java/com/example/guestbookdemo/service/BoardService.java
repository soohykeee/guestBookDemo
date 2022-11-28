package com.example.guestbookdemo.service;

import com.example.guestbookdemo.dto.BoardDTO;
import com.example.guestbookdemo.entity.Board;
import com.example.guestbookdemo.entity.Member;

public interface BoardService {

    Long register(BoardDTO dto);

    default Board dtoToEntity(BoardDTO dto) {
        Member member = Member.builder().email(dto.getWriterEmail()).build();

        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return board;
    }
}
