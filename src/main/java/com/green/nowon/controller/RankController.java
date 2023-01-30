package com.green.nowon.controller;

import com.green.nowon.domain.dto.RankInsertDTO;
import com.green.nowon.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RankController {
    @Autowired
    private RankService rankService;



    @GetMapping("/member/rankRegistration") //등록페이지에서 계급 리스트 뿌려줌
    public String rank(){
        return "member/rank";
    }


    @GetMapping("/member/registration") //사원등록 페이지
    public String rankRegistration(Model model) {
        rankService.rank(model);
        return "member/member";
    }


//    @ResponseBody
    @PostMapping("/rankInsert")//계급(장) 등록
    public String rankInsert(RankInsertDTO dto){
        rankService.save(dto);
        return "member/rank";
    }
//    @GetMapping("/rankInsert")
//    public String rankInsert(){return "member/rank";}


}
