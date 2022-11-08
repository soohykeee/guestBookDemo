package com.example.guestbookdemo.controller;

import com.example.guestbookdemo.dto.PageRequestDTO;
import com.example.guestbookdemo.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestbookService service;

    @GetMapping("/")
    public String list() {
        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    @ModelAttribute("pageRequestDTO")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        log.info("list..................." + pageRequestDTO);

        model.addAttribute("result", service.getList(pageRequestDTO));
    }
}
