package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.RankImgEntity;
import lombok.Getter;

@Getter
public class RankImgDTO {
    private long no;
    private String orgName;
    private String newName;
    private String url;

    private String imgUrl;

    private String position;

    public RankImgDTO(RankImgEntity e) {
        this.no = e.getNo();
        this.orgName = e.getNewName();
        this.newName = e.getNewName();
        this.url = e.getUrl();
        this.imgUrl=url+newName;
    }
}
