package com.example.guestbookdemo.repository;

import com.example.guestbookdemo.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

}
