package com.wyzdev.sunrisetv.models;

/**
 * Created by Noel Emmanuel Roodly on 6/19/2019.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Customer  implements Serializable {
    @SerializedName("id") @Expose public Long id;
    @SerializedName("first_name") @Expose public String firstName;
    @SerializedName("last_name") @Expose public String lastName;
    @SerializedName("created") @Expose public String created;
    @SerializedName("modified") @Expose public String modified;
    @SerializedName("email") @Expose public String email;
    @SerializedName("phone") @Expose public String phone;
    @SerializedName("address") @Expose public String adresse;
    @SerializedName("discount") @Expose public Double discount;
    @SerializedName("balance") @Expose public Double balance;
    @SerializedName("discount_type") @Expose public int discountType;
    @SerializedName("credit_limit") @Expose public String limitcredit;
    @SerializedName("rate_id") @Expose public Integer rateId;
    @SerializedName("type") @Expose public Integer type;
    @SerializedName("user_id") @Expose public Integer userId;
    @SerializedName("status") @Expose public Integer active;

    public Customer(long id) {
        this.id = id;
    }

    public Customer() {
    }
}