package com.acsm.training.model.member;/**
 * Created by lq on 2018/2/2.
 */

import com.acsm.training.model.MemberCard;
import com.acsm.training.model.page.MemberModel;

import java.io.Serializable;
import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2018-02-02
 */
public class MemberViewModel implements Serializable{
    private static final long serialVersionUID = 1312175361560486946L;

    private List<MemberModel> cardList;
    private Integer code;
    private Integer canEdit;
    private Integer cardId;
    private Integer memberStatus;
    private MemberCard memberCard;
    private Integer thisCardVality;
    private String cardStartTime;

    public List<MemberModel> getCardList() {
        return cardList;
    }

    public void setCardList(List<MemberModel> cardList) {
        this.cardList = cardList;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Integer canEdit) {
        this.canEdit = canEdit;
    }

    public Integer getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(Integer memberStatus) {
        this.memberStatus = memberStatus;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getThisCardVality() {
        return thisCardVality;
    }

    public void setThisCardVality(Integer thisCardVality) {
        this.thisCardVality = thisCardVality;
    }

    public MemberCard getMemberCard() {
        return memberCard;
    }

    public void setMemberCard(MemberCard memberCard) {
        this.memberCard = memberCard;
    }

    public String getCardStartTime() {
        return cardStartTime;
    }

    public void setCardStartTime(String cardStartTime) {
        this.cardStartTime = cardStartTime;
    }
}
