package com.wyzdev.sunrisetv.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Receiver  implements Serializable {
    @SerializedName("id") @Expose public Long id;
    @SerializedName("name") @Expose public String name;
    @SerializedName("email") @Expose public String email;
    @SerializedName("phone") @Expose public String phone;
    @SerializedName("address") @Expose public String adresse;
    @SerializedName("created") @Expose public String created;
    @SerializedName("modified") @Expose public String modified;
}
