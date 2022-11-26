package com.example.guestbookdemo.repository;

import com.example.guestbookdemo.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
