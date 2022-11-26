package com.example.guestbookdemo.repository;

import com.example.guestbookdemo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

}
