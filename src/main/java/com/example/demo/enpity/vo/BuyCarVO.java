package com.example.demo.enpity.vo;

import java.util.ArrayList;
import java.util.List;

public class BuyCarVO {
    private String sj;
    private boolean select = false;
    private boolean show = true;
    private List<BuyCarListVO> items = new ArrayList<BuyCarListVO>();

    @Override
    public String toString() {
        return "BuyCarVO{" +
                "sj='" + sj + '\'' +
                ", select=" + select +
                ", show=" + show +
                ", items=" + items +
                '}';
    }

    public BuyCarVO() {
    }

    public String getSj() {
        return sj;
    }

    public void setSj(String sj) {
        this.sj = sj;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public List<BuyCarListVO> getItems() {
        return items;
    }

    public void setItems(List<BuyCarListVO> items) {
        this.items = items;
    }
}
