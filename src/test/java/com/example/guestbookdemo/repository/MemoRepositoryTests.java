package com.example.guestbookdemo.repository;

import com.example.guestbookdemo.entity.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests {

    @Autowired
    MemoRepository memoRepository;

    // 삽입 테스트
    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 100).forEach(i ->{
            Memo memo = Memo.builder().memoText("Sample..."+i).build();
            memoRepository.save(memo);
        });
    }

    // 선택 테스트
    @Test
    public void testSelect1() {
        Long mno = 100L;
        Optional<Memo> result = memoRepository.findById(mno);
        System.out.println("==========TestSelect1===========");
        if (result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }
    @Test
    @Transactional
    public void testSelect2() {
        Long mno = 100L;
        Memo memo = memoRepository.getOne(mno);
        System.out.println("==========TestSelect2===========");
        System.out.println(memo);
    }

    //수정 테스트
    @Test
    public void testUpdate() {
        Memo memo = Memo.builder().mno(100L).memoText("Update Texts").build();
        System.out.println(memoRepository.save(memo));
    }

    //삭제 테스트
    @Test
    public void testDelete() {
        Long mno = 100L;
        if (memoRepository.findById(mno).isPresent()) {
            memoRepository.deleteById(mno);
            System.out.println("---------Delete Success--------");
        } else {
            System.out.println("--------Delete Failed---------");
        }
    }

    //페이징 테스트
    @Test
    public void testPageDefault() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Memo> result = memoRepository.findAll(pageable);
        
        System.out.println(result);
        System.out.println("-----------testPageDefault------------");
        System.out.println("TotalPages: " + result.getTotalPages());    //총 페이지
        System.out.println("TotalCount: " + result.getTotalElements()); //전체 개수
        System.out.println("PageNumber: " + result.getNumber());        //현재 페이지 번호
        System.out.println("Page Size: " + result.getSize());           //페이지당 데이터 개수
        System.out.println("has next page?: " + result.hasNext());      //다음 페이지
        System.out.println("first page?: " + result.isFirst());         //시작 페이지(0) 여부
    }

    //정렬 테스트
    @Test
    public void testSort() {
        Sort sort = Sort.by("mno").descending();
        Pageable pageable = PageRequest.of(0, 10, sort);
        Page<Memo> result = memoRepository.findAll(pageable);

        result.get().forEach(memo -> {
            System.out.println(memo);
        });
    }
}
