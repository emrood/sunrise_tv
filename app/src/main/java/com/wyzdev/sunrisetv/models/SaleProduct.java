package com.wyzdev.sunrisetv.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



/**
 * Created by Noel Emmanuel Roodly on 9/20/2019.
 */

public class SaleProduct {

    @SerializedName("product_id") @Expose public Integer productId;
    @SerializedName("sale_number") @Expose public String sale_number;
}
