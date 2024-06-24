package com.o2.site.club.dto;

import java.util.Date;

public class ClubDTO {

    private String clubName;
    private long readerNo;
    private String categoryId;
    private int membersLimit;
    private int ageLimitStart;
    private int ageLimitEnd;
    private String area;
    private String oneLineIntro;
    private String intro;
    private String thumbnail;
    private Date createDate;

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public long getReaderNo() {
        return readerNo;
    }

    public void setReaderNo(long readerNo) {
        this.readerNo = readerNo;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getMembersLimit() {
        return membersLimit;
    }

    public void setMembersLimit(int membersLimit) {
        this.membersLimit = membersLimit;
    }

    public int getAgeLimitStart() {
        return ageLimitStart;
    }

    public void setAgeLimitStart(int ageLimitStart) {
        this.ageLimitStart = ageLimitStart;
    }

    public int getAgeLimitEnd() {
        return ageLimitEnd;
    }

    public void setAgeLimitEnd(int ageLimitEnd) {
        this.ageLimitEnd = ageLimitEnd;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOneLineIntro() {
        return oneLineIntro;
    }

    public void setOneLineIntro(String oneLineIntro) {
        this.oneLineIntro = oneLineIntro;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "com.o2.site.club.dto.ClubDTO{" +
                "clubName='" + getClubName() + '\'' +
                ", readerNo=" + getReaderNo() +
                ", categoryId='" + getCategoryId() + '\'' +
                ", membersLimit=" + getMembersLimit() +
                ", ageLimitStart=" + getAgeLimitStart() +
                ", ageLimitEnd=" + getAgeLimitEnd() +
                ", area='" + getArea() + '\'' +
                ", oneLineIntro='" + getOneLineIntro() + '\'' +
                ", intro='" + getIntro() + '\'' +
                ", thumbnail='" + getThumbnail() + '\'' +
                ", createDate=" + getCreateDate() +
                '}';
    }
}
