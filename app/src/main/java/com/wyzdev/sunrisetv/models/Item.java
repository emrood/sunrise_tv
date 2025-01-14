package com.wyzdev.sunrisetv.models;

/**
 * Created by Noel Emmanuel Roodly on 6/19/2019.
 */

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.Serializable;


public class Item implements Serializable {
    @SerializedName("id") public Long id;
    @SerializedName("quantity") @Expose public Double qte;
    @SerializedName("length") @Expose public Double length = 0.0;
    @SerializedName("width") @Expose public Double width = 0.0;
    @SerializedName("height") @Expose public Double height = 0.0;
    @SerializedName("product_id") @Expose public Integer productId;
    @SerializedName("editable") @Expose public Integer editable = 0;
    @SerializedName("status") @Expose public Integer status;
    @SerializedName("price") @Expose public Double prix;
    @SerializedName("taxe") @Expose public Double taxe;
    @SerializedName("totalPrice") @Expose public Double totalPrice;
    @SerializedName("barcode") @Expose public String barcode;
    @SerializedName("comment") @Expose public String comment;
    @SerializedName("tag") @Expose public String tag;
    @SerializedName("image_path") @Expose public String imagePath;
    @SerializedName("flight_id") @Expose public Integer flightId;
    @SerializedName("truck_id") @Expose public Integer truckId;
    @SerializedName("is_loaded") @Expose public Integer isLoaded;
    @SerializedName("is_landed") @Expose public Integer isLanded;
    @SerializedName("is_delivered") @Expose public Integer isDelivered;
    @SerializedName("sale") @Expose public Sale sale;
    public Double basePrice;
    public String sale_number;
    public String image_name = null;
    public Bitmap photo = null;
    public File image_file = null;
    public Bitmap bitmap = null;
}
