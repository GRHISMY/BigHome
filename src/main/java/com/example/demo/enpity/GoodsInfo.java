package com.example.demo.enpity;

import java.io.Serializable;
import java.util.List;

public class GoodsInfo implements Serializable {
    private Integer goods_id;
    private String goods_name;
    private String goods_describe;
    private float goods_price;
    private float goods_actual_price;
    private Integer goods_stock;
    private String goods_begin_time;
    private String goods_end_time;
    private Integer goods_status;
    private Integer store_id;
    private String dictionary_code;
    private List<GoodsPropertyInfo> goods_property_infoList = null; //解决1对多
    private List<GoodsPhotoPath_Info> goods_photo_path_infoList = null;//解决1对多

    @Override
    public String toString() {
        return "GoodsInfo{" +
                "goods_id=" + goods_id +
                ", goods_name='" + goods_name + '\'' +
                ", goods_describe='" + goods_describe + '\'' +
                ", goods_price=" + goods_price +
                ", goods_actual_price=" + goods_actual_price +
                ", goods_stock=" + goods_stock +
                ", goods_begin_time='" + goods_begin_time + '\'' +
                ", goods_end_time='" + goods_end_time + '\'' +
                ", goods_status=" + goods_status +
                ", store_id=" + store_id +
                ", dictionary_code='" + dictionary_code + '\'' +
                ", goods_property_infoList=" + goods_property_infoList +
                ", goods_photo_path_infoList=" + goods_photo_path_infoList +
                '}';
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_describe() {
        return goods_describe;
    }

    public void setGoods_describe(String goods_describe) {
        this.goods_describe = goods_describe;
    }

    public float getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(float goods_price) {
        this.goods_price = goods_price;
    }

    public float getGoods_actual_price() {
        return goods_actual_price;
    }

    public void setGoods_actual_price(float goods_actual_price) {
        this.goods_actual_price = goods_actual_price;
    }

    public Integer getGoods_stock() {
        return goods_stock;
    }

    public void setGoods_stock(Integer goods_stock) {
        this.goods_stock = goods_stock;
    }

    public String getGoods_begin_time() {
        return goods_begin_time;
    }

    public void setGoods_begin_time(String goods_begin_time) {
        this.goods_begin_time = goods_begin_time;
    }

    public String getGoods_end_time() {
        return goods_end_time;
    }

    public void setGoods_end_time(String goods_end_time) {
        this.goods_end_time = goods_end_time;
    }

    public Integer getGoods_status() {
        return goods_status;
    }

    public void setGoods_status(Integer goods_status) {
        this.goods_status = goods_status;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    public String getDictionary_code() {
        return dictionary_code;
    }

    public void setDictionary_code(String dictionary_code) {
        this.dictionary_code = dictionary_code;
    }

    public List<GoodsPropertyInfo> getGoods_property_infoList() {
        return goods_property_infoList;
    }

    public void setGoods_property_infoList(List<GoodsPropertyInfo> goods_property_infoList) {
        this.goods_property_infoList = goods_property_infoList;
    }

    public List<GoodsPhotoPath_Info> getGoods_photo_path_infoList() {
        return goods_photo_path_infoList;
    }

    public void setGoods_photo_path_infoList(List<GoodsPhotoPath_Info> goods_photo_path_infoList) {
        this.goods_photo_path_infoList = goods_photo_path_infoList;
    }
}
