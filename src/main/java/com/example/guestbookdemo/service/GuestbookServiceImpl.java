package com.example.guestbookdemo.service;

import com.example.guestbookdemo.dto.GuestbookDTO;
import com.example.guestbookdemo.entity.Guestbook;
import com.example.guestbookdemo.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository repository;

    @Override
    public Long register(GuestbookDTO guestbookDTO) {

        Guestbook entity = dtoToEntity(guestbookDTO);

        repository.save(entity);

        return entity.getGno();
    }
}
