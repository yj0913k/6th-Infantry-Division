package com.green.nowon.service.Impl;

import com.green.nowon.domain.dto.RankInsertDTO;
import com.green.nowon.domain.dto.RankListDTO;
import com.green.nowon.domain.entity.RankEntity;
import com.green.nowon.repository.RankEntityRepository;
import com.green.nowon.repository.RankImgRepository;
import com.green.nowon.service.RankService;
import com.green.nowon.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RankServiceProcess implements RankService {
    @Value("${file.location.temp}")
    private String locationTemp;

    @Value("${file.location.upload}")
    private String locationUpload;

    @Autowired
    private RankEntityRepository rankEntityRepository;
    @Autowired
    private RankImgRepository rankImgRepository;

    @Override
    public void save(RankInsertDTO dto){
        //카테고리와 상품 등록
        //이미지 정보 등록, temp->실제 upload위치
        dto.toRankEntity();
//
        RankEntity entity=rankEntityRepository.save(dto.toRankEntity());
//        for(long no:categoryNo) {
//            cateItemRepo.save(CategoryItemEntity.builder()
//                    .item(entity)
//                    .category(cateRepo.findById(no).get())
//                    .build());
//        }나중에 계급 등록 할 때 카테고리. 계급.

        dto.toRankListImgs(entity, locationUpload).forEach(rankEntityRepository::save);
        //이미지 temp->temp->실제 upload위치
    }

    @Override
    public Map<String, String> fileTempUpload(MultipartFile gimg) {
        return FileUtils.fileUpload(gimg, locationTemp);    }

    @Transactional
    @Override
    public void rank(Model model) {
        model.addAttribute("rList", rankEntityRepository.findAll()
                .stream()
                .map(RankListDTO::new)
                .collect(Collectors.toList()));

    }






}
