package com.green.nowon.service.Impl;

import com.green.nowon.domain.dto.BoarderInsertDTO;
import com.green.nowon.mapper.BoardMapper;
import com.green.nowon.service.BoardService;
import com.green.nowon.util.PageDTO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.List;

@Repository
public class BoardServiceProcess implements BoardService {



    @Autowired
    private BoardMapper mapper;


    //검색
    @Override
	public void getBoardList(Model model, int page, BoarderInsertDTO dto) {
    	int limit=5;//한페이지에 몇줄
        int offset=(page-1)*limit;//건너뛰는거
        RowBounds rb=new RowBounds(offset, limit);

        model.addAttribute("list", mapper.getBoardListOfSearch(dto,rb));

        int rowTotal=mapper.countBoardListOfSearch(dto);
        System.out.println(">>>:"+rowTotal);
        model.addAttribute("pageinfo",PageDTO.getInstance(page, rowTotal, limit, 2));

	}

    @Override
    public void indexlist(Model model) {
        
    }

    @Override
    public void updatecount(long no, BoarderInsertDTO dto) {
        BoarderInsertDTO result=mapper.findById(no);
        if(result != null) {
            result.setContent(dto.getContent());//내용수정
            mapper.updatecount(result);
        }
    }


    //인증된 게시글 저장
    @Override
    public void boardsave(BoarderInsertDTO dto, long eno, String eName) {
        dto.setWriter(eName);
        dto.setEmployeeNo(eno);
        mapper.save(dto);
    }

    ////게시글 보여주기
    @Override
    public void findAll(Model model) {

        List<BoarderInsertDTO> result= mapper.findAll();
        model.addAttribute("list", result);
    }

    @Override //게시글 상세보기
    public void detail(long no, Model model) {
        model.addAttribute("detail", mapper.findById(no));
    }

    @Override //게시글 삭제
    public void delete(long no, Model model) {
        mapper.deleteById(no);
    }

    @Override //게시즐 수정
    public void update(long no, BoarderInsertDTO dto) {
        BoarderInsertDTO result=mapper.findById(no);
        if(result != null) {
            result.setTitle(dto.getTitle());//제목수정
            result.setContent(dto.getContent());//내용수정
            mapper.update(result);
        }
    }




}


