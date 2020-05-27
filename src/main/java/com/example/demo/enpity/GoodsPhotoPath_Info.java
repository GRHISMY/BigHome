package com.example.demo.enpity;

import java.io.Serializable;

public class GoodsPhotoPath_Info implements Serializable {
    private Integer path_id;
    private Integer goods_id;
    private String path_name;

    @Override
    public String toString() {
        return "GoodsPhotoPath_Info{" +
                "path_id=" + path_id +
                ", goods_id=" + goods_id +
                ", path_name='" + path_name + '\'' +
                '}';
    }

    public Integer getPath_id() {
        return path_id;
    }

    public void setPath_id(Integer path_id) {
        this.path_id = path_id;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public String getPath_name() {
        return path_name;
    }

    public void setPath_name(String path_name) {
        this.path_name = path_name;
    }
}
