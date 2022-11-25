package com.example.guestbookdemo.service;

import com.example.guestbookdemo.dto.GuestbookDTO;
import com.example.guestbookdemo.dto.PageRequestDTO;
import com.example.guestbookdemo.dto.PageResultDTO;
import com.example.guestbookdemo.entity.Guestbook;
import com.example.guestbookdemo.entity.QGuestbook;
import com.example.guestbookdemo.repository.GuestbookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

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

    @Override
    public GuestbookDTO read(Long gno) {
        Optional<Guestbook> result = repository.findById(gno);
        return result.isPresent() ? entityToDto(result.get()) : null;
    }

    @Override
    public void remove(Long gno) {
        repository.deleteById(gno);
    }

    @Override
    public void modify(GuestbookDTO dto) {
        Optional<Guestbook> result = repository.findById(dto.getGno());

        if (result.isPresent()) {
            Guestbook entity = result.get();
            entity.changeTitle(dto.getTitle());
            entity.changeContent(dto.getContent());
            repository.save(entity);
        }
    }

    @Override
    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDTO);

        Page<Guestbook> result = repository.findAll(booleanBuilder, pageable);

        Function<Guestbook, GuestbookDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        String type = requestDTO.getType();
        String keyword = requestDTO.getKeyword();
        QGuestbook qGuestbook = QGuestbook.guestbook;

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        BooleanExpression expression = qGuestbook.gno.gt(0L);       //gno > 0 조건 생성
        booleanBuilder.and(expression);

        if (type == null || type.trim().length() == 0) {        // 검색 type이 없는 경우
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if (type.contains("t")) {
            conditionBuilder.or(qGuestbook.title.contains(keyword));
        } if (type.contains("c")) {
            conditionBuilder.or(qGuestbook.content.contains(keyword));
        } if (type.contains("w")) {
            conditionBuilder.or(qGuestbook.writer.contains(keyword));
        }

        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }


}
