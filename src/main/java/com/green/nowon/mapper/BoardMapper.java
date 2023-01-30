package com.green.nowon.mapper;

import com.green.nowon.domain.dto.BoarderInsertDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


@Mapper
public interface BoardMapper {
    void save(BoarderInsertDTO dto);

    List<BoarderInsertDTO> findAll();

    BoarderInsertDTO findById(long no);

    void deleteById(long no);

    void update(BoarderInsertDTO result);


    int countBoardListOfSearch(BoarderInsertDTO dto);

	List<BoarderInsertDTO> getBoardListOfSearch(BoarderInsertDTO dto, RowBounds rb);

    void updatecount(BoarderInsertDTO result);
}