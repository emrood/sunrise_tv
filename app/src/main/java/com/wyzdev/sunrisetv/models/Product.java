package com.wyzdev.sunrisetv.models;

/**
 * Created by Noel Emmanuel Roodly on 6/19/2019.
 */



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Product {
    @SerializedName("id") @Expose public Long id;
    @SerializedName("name") @Expose public String name;
    @SerializedName("cash_price") @Expose public Double cash_price;
    @SerializedName("credit_price") @Expose public Double credit_price;
    @SerializedName("barcode") @Expose public String barcode;
    @SerializedName("qte") @Expose public Integer qte;
    @SerializedName("category_id") @Expose public Integer categorie;
    @SerializedName("image") @Expose public String image;
    @SerializedName("status") @Expose public Integer status;
    @SerializedName("created") @Expose public String created;
    @SerializedName("modified") @Expose public String modified;
}