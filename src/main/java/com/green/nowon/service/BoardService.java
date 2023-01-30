package com.green.nowon.service;

import com.green.nowon.domain.dto.BoarderInsertDTO;
import org.springframework.ui.Model;

public interface BoardService {
    void boardsave(BoarderInsertDTO dto, long eno, String eName);

    void findAll(Model model);

    void detail(long no, Model model);

    void delete(long no, Model model);

    void update(long no, BoarderInsertDTO dto);


	void getBoardList(Model model, int page, BoarderInsertDTO dto);

    void indexlist(Model model);

    void updatecount(long no, BoarderInsertDTO dto);
}
