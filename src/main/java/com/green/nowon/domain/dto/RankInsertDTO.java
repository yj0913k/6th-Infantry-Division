package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.RankEntity;
import com.green.nowon.domain.entity.RankImgEntity;
import com.green.nowon.util.FileUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class RankInsertDTO {
	private long no;
	private String position;
	private long basePay;
	private long nightPay;
	private long overPay;
	private long annualPay;
	private int annualDay;
	private long totAnnualPay;

	private String[] newName;
	private String[] orgName;

	public List<RankImgEntity> toRankListImgs(RankEntity rank,String url){
		List<RankImgEntity> imgs= new ArrayList<>();
		for(int i=0; i<orgName.length; i++) {
			if(orgName[i].equals("") || orgName[i]==null)continue;

			boolean defImg = true;
			if(i==0)defImg= true;

			RankImgEntity ent=RankImgEntity.builder()
					.url(url)
					.orgName(orgName[i])
					.newName(newName[i])
					.defImg(defImg)
					.rank(rank)
					.build();
				imgs.add(ent);
		}
		FileUtils.moveUploadLocationFromTemp(newName, url);

		return imgs;
	}

	public RankEntity toRankEntity() {
		return RankEntity.builder()
				.no(no)
				.position(position)
				.basePay(basePay)
				.nightPay(nightPay)
				.overPay(overPay)
				.annualPay(annualPay)
				.annualDay(annualDay)
				.build();
	}

}
