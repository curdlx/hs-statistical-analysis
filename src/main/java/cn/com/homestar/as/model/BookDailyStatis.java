package cn.com.homestar.as.model;

import java.util.Date;

/**
 * 每日书籍日志统计
 */
public class BookDailyStatis {
    private Integer id;
    private Integer bookId;//书籍id
    private Integer viewCcount;//当天浏览量
    private Integer downCount;//当天下载量
    private Date date;//哪天，yyyy-MM-dd
    private Date createTime;//记录创建时间


}
