package com.example.guestbookdemo.repository;

import com.example.guestbookdemo.entity.Board;
import com.example.guestbookdemo.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // board 삭제시 댓글들 삭제해주는 메소드
    @Modifying
    @Query("delete from Reply r where r.board.bno=:bno")
    void deleteByBno(Long bno);

    // 게시물로 댓글 목록 가져오기
    List<Reply> getRepliesByBoardOrderByRno(Board board);

}
