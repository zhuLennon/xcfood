package zhu.xin.guang.domain;

import java.util.List;

/**
 * 美食的实体类
 */
public class Food {

    private int fid;//美食的id
    private String fname;//美食名称，不为空
    private double price;//价格，不为空
    private int count;//点赞数量
    private String fimage;//缩略图

//    构造方法
    public Food(int fid, String fname, double price, int count, String fimage) {
        this.fid = fid;
        this.fname = fname;
        this.price = price;
        this.count = count;
        this.fimage = fimage;
    }

    public Food() {
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFimage() {
        return fimage;
    }

    public void setFimage(String fimage) {
        this.fimage = fimage;
    }
}
