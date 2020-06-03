package com.example.demo.enpity.vo;

public class GoodsVO {
    private Integer num;
    private float price;
    private String desc;
    private String title;
    private String thumb;


    @Override
    public String toString() {
        return "GoodsVO{" +
                "num=" + num +
                ", price=" + price +
                ", desc='" + desc + '\'' +
                ", title='" + title + '\'' +
                ", thumb='" + thumb + '\'' +
                '}';
    }

    public GoodsVO() {
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
