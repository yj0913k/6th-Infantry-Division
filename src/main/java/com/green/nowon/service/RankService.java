package com.green.nowon.service;

import com.green.nowon.domain.dto.RankInsertDTO;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface RankService {
    void save(RankInsertDTO dto);

    Map<String, String> fileTempUpload(MultipartFile gimg);

    void rank(Model model);
}
