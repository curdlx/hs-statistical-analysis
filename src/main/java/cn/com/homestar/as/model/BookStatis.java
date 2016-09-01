package cn.com.homestar.as.model;

import java.util.Date;

/**
 * 书籍统计
 */

public class BookStatis {

    private Long id;
    private Long totalClick;//总访问量
    private Long totalDown;//总下载量
    private Integer weekClick;//本周下载量
    private Integer weekDown;//本周下载量
    private Integer mouthClick;//本月下载量（记录不记录待定）
    private Integer mouthDown;//本月下载量（记录不记录待定）
    private Integer adjustClick;//点击调整量
    private Integer adjustDown;//下载调整量
    private Integer totalBuy;//购买次数
    private Integer weekBuy;//周购买
    private Integer mouthBuy;//月购买
    private Date createTime;//创建时间
    private Date updateTime;//更改时间
    private Integer operatorId; //操作更改者id

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalClick() {
        return totalClick;
    }

    public void setTotalClick(Long totalClick) {
        this.totalClick = totalClick;
    }

    public Long getTotalDown() {
        return totalDown;
    }

    public void setTotalDown(Long totalDown) {
        this.totalDown = totalDown;
    }

    public Integer getWeekClick() {
        return weekClick;
    }

    public void setWeekClick(Integer weekClick) {
        this.weekClick = weekClick;
    }

    public Integer getWeekDown() {
        return weekDown;
    }

    public void setWeekDown(Integer weekDown) {
        this.weekDown = weekDown;
    }

    public Integer getMouthClick() {
        return mouthClick;
    }

    public void setMouthClick(Integer mouthClick) {
        this.mouthClick = mouthClick;
    }

    public Integer getMouthDown() {
        return mouthDown;
    }

    public void setMouthDown(Integer mouthDown) {
        this.mouthDown = mouthDown;
    }

    public Integer getAdjustClick() {
        return adjustClick;
    }

    public void setAdjustClick(Integer adjustClick) {
        this.adjustClick = adjustClick;
    }

    public Integer getAdjustDown() {
        return adjustDown;
    }

    public void setAdjustDown(Integer adjustDown) {
        this.adjustDown = adjustDown;
    }

    public Integer getTotalBuy() {
        return totalBuy;
    }

    public void setTotalBuy(Integer totalBuy) {
        this.totalBuy = totalBuy;
    }

    public Integer getWeekBuy() {
        return weekBuy;
    }

    public void setWeekBuy(Integer weekBuy) {
        this.weekBuy = weekBuy;
    }

    public Integer getMouthBuy() {
        return mouthBuy;
    }

    public void setMouthBuy(Integer mouthBuy) {
        this.mouthBuy = mouthBuy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }
}
