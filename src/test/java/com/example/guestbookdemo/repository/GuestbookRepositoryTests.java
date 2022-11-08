package com.example.guestbookdemo.repository;

import com.example.guestbookdemo.entity.Guestbook;
import com.example.guestbookdemo.entity.QGuestbook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTests {

    @Autowired
    private GuestbookRepository guestbookRepository;

    // 데이터 삽입 테스트
    @Test
    public void insertGuestbook() {
        IntStream.rangeClosed(1,300).forEach( i -> {
            Guestbook guestbook = Guestbook.builder()
                    .title("Title..." + i)
                    .content("Content..." + i)
                    .writer("user" + (i % 10))
                    .build();
            System.out.println(guestbookRepository.save(guestbook));
        });
    }

    // querydsl을 이용한 검색 테스트 + 페이징
    @Test
    public void testQuerydsl1() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno"));  //페이징
        String keyword = "1";       //검색할 단어

        QGuestbook qGuestbook = QGuestbook.guestbook;   //1

        BooleanBuilder builder = new BooleanBuilder();  //2

        BooleanExpression expression = qGuestbook.title.contains(keyword);  //3

        builder.and(expression);    //4

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);    //5

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });
    }
}
