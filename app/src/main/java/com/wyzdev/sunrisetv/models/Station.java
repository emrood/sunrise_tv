package com.wyzdev.sunrisetv.models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;



public class Station  implements Serializable {
    @SerializedName("id") @Expose public Long id;
    @SerializedName("name") @Expose public String name;
    @SerializedName("abbreviation") @Expose public String abbreviation;
    @SerializedName("taxe") @Expose public Double taxe = 1.0;
    @SerializedName("rate_id") @Expose public Long rateId = 1L;
    @SerializedName("phone") @Expose @Nullable public String phone;
    @SerializedName("email") @Expose @Nullable public String email;
    @SerializedName("address") @Expose @Nullable public String address;
    @SerializedName("hexa") @Expose @Nullable public String hexa;
    @SerializedName("logo_path") @Expose @Nullable public String logoPath;
    public String filePath;
    public String localPath;
    @SerializedName("file_name") @Expose @Nullable public String fileName;
}
