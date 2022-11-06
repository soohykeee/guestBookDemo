package com.example.guestbookdemo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mno;

    @Column(length = 200, nullable = false)
    private String memoText;

}
