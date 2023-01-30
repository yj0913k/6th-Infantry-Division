package com.green.nowon.controller;

import com.green.nowon.domain.dto.BoarderInsertDTO;
import com.green.nowon.security.MyUserDetails;
import com.green.nowon.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//Mybatis
public class BoardController {

    @Autowired
    private BoardService service;

    @PostMapping("/write") //
    public String boardsave(BoarderInsertDTO dto, @AuthenticationPrincipal MyUserDetails userDetails) {
        long eno = userDetails.getMno();
        String eName = userDetails.getName();
        //String writer = userDetails.getName();

        service.boardsave(dto, eno, eName);
        return "redirect:/list";
    }
    
    @GetMapping("/list")
   	public String list() {
           return "list/list";
    }

    @GetMapping("/getListAll")
	public String list2(Model model, final int page,
			BoarderInsertDTO dto) {
        service.getBoardList(model,page, dto);
        return "list/search-list";
    }



    @GetMapping("/detail/{no}")
    public String detail(@PathVariable long no, Model model , BoarderInsertDTO dto) {
        service.updatecount(no, dto);
        service.detail(no, model);
        return "list/detail";
    }


    @DeleteMapping("/detail/{no}")  //삭제
    public String delete(@PathVariable long no, Model model) {
        service.delete(no, model);
        return "redirect:/list";
    }

    @PutMapping("/detail/{no}")  //삭제
    public String update(@PathVariable long no, BoarderInsertDTO dto) {
        service.update(no, dto);
        return "redirect:/detail/"+no;
    }


    @GetMapping("/write") //글쓰기페이지이동
    public String write() {
        return "list/write";
    }


    @GetMapping("/user/login")
    public String login() {
        return "log/login";
    }
    
//    @GetMapping("/")
//    public String indexlist(Model model) {
//    	service.indexlist(model);
//    	return "index";
//    }
}