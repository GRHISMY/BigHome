package com.example.demo.enpity.vo;

public class BuyCarListVO {
    private String animateAn;
    private String animate;
    private Integer id;
    private String cp;
    private float jg;
    private Integer sl;
    private boolean select = false;
    private String img;

    @Override
    public String toString() {
        return "BuyCarListVO{" +
                "animateAn='" + animateAn + '\'' +
                ", animate='" + animate + '\'' +
                ", id=" + id +
                ", cp='" + cp + '\'' +
                ", jg=" + jg +
                ", sl=" + sl +
                ", select=" + select +
                ", img='" + img + '\'' +
                '}';
    }

    public BuyCarListVO() {
    }

    public String getAnimateAn() {
        return animateAn;
    }

    public void setAnimateAn(String animateAn) {
        this.animateAn = animateAn;
    }

    public String getAnimate() {
        return animate;
    }

    public void setAnimate(String animate) {
        this.animate = animate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public float getJg() {
        return jg;
    }

    public void setJg(float jg) {
        this.jg = jg;
    }

    public Integer getSl() {
        return sl;
    }

    public void setSl(Integer sl) {
        this.sl = sl;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
