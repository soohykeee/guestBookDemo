package com.example.guestbookdemo.controller;

import com.example.guestbookdemo.dto.GuestbookDTO;
import com.example.guestbookdemo.dto.PageRequestDTO;
import com.example.guestbookdemo.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/register")
    public void register() {
        log.info("Get - register--------------r");
    }

    @PostMapping("/register")
    public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes) {

        log.info("registerPost Method ---- DTO : " + dto);

        Long gno = service.register(dto);   // dto를 통해 입력한 정보를 등록하고, 등록한 gno를 가져온다.

        //추후에 modal 창을 통해 등록된 gno 정보를 뿌려주기 위해서
        //post 방식에서 한번 데이터를 전달하는 용도로 사용하기 위해 작성
        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/guestbook/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        log.info("Read - gno : " + gno);
        GuestbookDTO dto = service.read(gno);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String modify(GuestbookDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
        log.info("Post Modify..............");
        log.info("Modify - dto : " + dto);

        service.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("gno", dto.getGno());

        return "redirect:/guestbook/read";
    }

    @PostMapping("/remove")
    public String remove(long gno, RedirectAttributes redirectAttributes) {
        log.info("remove - gno : " + gno);
        service.remove(gno);
        redirectAttributes.addFlashAttribute("msg", gno);
        return "redirect:/guestbook/list";
    }
}
