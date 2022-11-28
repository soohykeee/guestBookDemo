package com.example.guestbookdemo.service;

import com.example.guestbookdemo.dto.BoardDTO;
import com.example.guestbookdemo.entity.Board;
import com.example.guestbookdemo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO dto) {
        Board board = dtoToEntity(dto);

        boardRepository.save(board);

        return board.getBno();
    }

}
