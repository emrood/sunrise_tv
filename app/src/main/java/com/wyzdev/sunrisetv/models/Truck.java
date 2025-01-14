package com.wyzdev.sunrisetv.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;



/**
 * Created by Noel Emmanuel Roodly on 7/6/2019.
 */
public class Truck  implements Serializable {

    @SerializedName("id") @Expose public Long id;
    @SerializedName("immatriculation") @Expose public String immatriculation;
    @SerializedName("name") @Expose public String name;
    @SerializedName("photo") @Expose public String photo;
    @SerializedName("volume") @Expose public Double volume;
//    @SerializedName("price") @Expose public Double price;
//    @SerializedName("taxe") @Expose public Double taxe;
    @SerializedName("min_weight") @Expose public Double minWeight;
    @SerializedName("max_weight") @Expose public Double maxWeight;
    @SerializedName("status") @Expose public Integer status;
    @SerializedName("created") @Expose public String created;
    @SerializedName("modified") @Expose public String modified;
    public String offline_path;

}
