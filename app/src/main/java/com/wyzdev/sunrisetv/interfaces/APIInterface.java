package com.wyzdev.sunrisetv.interfaces;


import androidx.annotation.Nullable;


import com.wyzdev.sunrisetv.models.ApiResponse;
import com.wyzdev.sunrisetv.models.Customer;
import com.wyzdev.sunrisetv.models.Flight;
import com.wyzdev.sunrisetv.models.Movement;
import com.wyzdev.sunrisetv.models.Product;
import com.wyzdev.sunrisetv.models.Receiver;
import com.wyzdev.sunrisetv.models.Sale;
import com.wyzdev.sunrisetv.models.SaleProduct;
import com.wyzdev.sunrisetv.models.SaleResponse;
import com.wyzdev.sunrisetv.models.Station;
import com.wyzdev.sunrisetv.models.Tracking;
import com.wyzdev.sunrisetv.models.Truck;
import com.wyzdev.sunrisetv.models.TruckStation;
import com.wyzdev.sunrisetv.models.User;
import com.wyzdev.sunrisetv.models.UserResponse;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Noel Emmanuel Roodly on 8/10/2019.
 */

public interface APIInterface {



    @Multipart
    @POST("sales/save_image")
    Call<ApiResponse> savePackageImage(@Part("file\"; filename=\"pp.png\" ") RequestBody file, @Part("file_name") RequestBody fileName);


    @POST("sales/package")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ApiResponse> savePackage(@Body Sale sale);

    @GET("products")
    Call<List<Product>> getProductsWithRelations(@Query("include") String relation);

    @GET("products")
    Call<List<Product>> getProductsList();

    @GET("users")
    Call<List<User>> getUsers();

    @GET("flights")
    Call<List<Flight>> getFlights();

    @GET("movements")
    Call<List<Movement>> getMovements();

    @GET("truck_stations")
    Call<List<TruckStation>> getTruckStations(@Nullable @Query("orderdesc") String orderDesc, @Nullable @Query("include") String include, @Nullable @Query("station_id") Long stationId);

    @GET("sales")
    Call<List<Sale>> searchSale(@Query("sale_number") String saleNumber);

    @GET("flights/packages")
    Call<List<Tracking>> getFlightPackages(@Nullable @Query("from") String from,@Nullable @Query("to") String to, @Query("flight_id") @Nullable Long flightId, @Query("movement_id") @Nullable Long movementId, @Query("station_id") Long stationId);

    @GET("customers/search")
    Call<List<Customer>> searchCustomer(@Query("text") String query);

    @GET("receivers/search")
    Call<List<Receiver>> searchReceiver(@Query("text") String query);


    @GET("receivers")
    Call<List<Receiver>> getReceivers();


    @GET("stations")
    Call<List<Station>> getStations();


    @GET("users/currentdate")
    Call<Long> getCurrentDate();

    @GET("users/report")
    Call<List<String>> getReport(@Query("user_id") Long userId, @Query("date") Long date);


    @GET("sales/distribute")
    Call<List<Sale>> getsales();

    @GET("sales/products")
    Call<List<SaleProduct>> getSalesProduct();
//    Call<List<Sale>> getsales(@Path("serial_number") String serial_number);

    @GET("sales/search/{sale_number}")
    Call<SaleResponse> searchTicketsByTokken(@Path("sale_number") String sale_number);



    @FormUrlEncoded
    @POST("auth/login")
    Call<UserResponse> login(@Field("username") String username, @Field("password") String password, @Field("serial_number") String serial_number);

    @FormUrlEncoded
    @POST("auth/login/card")
    Call<UserResponse> loginWithCard(@Field("card") String card, @Field("serial_number") String serial_number);

    @FormUrlEncoded
    @POST("auth/logout")
    Call<UserResponse> logout(@Field("user_id") Integer user_id, @Field("serial_number") String serial_number);

    @FormUrlEncoded
    @POST("auth/logout/card")
    Call<UserResponse> logoutWithCard(@Field("card") String card, @Field("serial_number") String serial_number);

    @FormUrlEncoded
    @POST("auth/permission")
    Call<UserResponse> permission(@Field("email_at") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/passwordcheck")
    Call<UserResponse> checkpassword(@Field("username") String email, @Field("password") String password, @Field("uid") String uid);


    @FormUrlEncoded
    @POST("sales/setloaded")
    Call<ApiResponse> setloaded(@Field("user_id") Long userId, @Field("sale_number") String saleNumber, @Field("date") String date);

    @FormUrlEncoded
    @POST("sales/setexit")
    Call<ApiResponse> setLanded(@Field("user_id") Long userId, @Field("sale_number") String saleNumber, @Field("date") String date);

    @FormUrlEncoded
    @POST("flights/packages/update")
    Call<ApiResponse> updatePackageStatus(@Field("user_id") Long userId, @Field("sale_id") Long saleId, @Field("barcode") String barcode, @Field("status") String status, @Nullable @Field("flight_id") Long flightId);

    @Multipart
    @POST("trucks/register")
    Call<ApiResponse> registerTruck(@Part("file\"; filename=\"pp.png\" ") RequestBody file, @Part("truck") RequestBody truck, @Part("user_id") RequestBody user_id);


    @Multipart
    @POST("requisitions/register")
    Call<ApiResponse> registerRequisition(@Part("file\"; filename=\"pp.png\" ") RequestBody file, @Part("requisition") RequestBody requisition, @Part("user_id") RequestBody user_id);


    @POST("trackings")
    @Headers(
            {
                    "Content-Type: application/json;charset=UTF-8",
            }
    )
    Call<Tracking> registerTracking(@Body Tracking tracking);


    @GET("sales/barcodes/search")
    Call<List<String>> getSuggestions(@Query("text") String query);

    @FormUrlEncoded
    @POST("receivings/terminated")
    Call<ApiResponse> validateReceiving(@Field("receiving_number") long receiving_number, @Field("user_id") int user_id);

    @GET("trucks")
    Call<List<Truck>> getListOfTrucks();

    @GET("customers")
    Call<List<Customer>> getListOfCustomers();

    @GET("customers/{id}")
    Call<Customer> getCustomer(@Path("id") Long id);


    @FormUrlEncoded
    @POST("users/supervisor_pin")
    Call<Boolean> supervisorvalidation(@Field("pin") String pin);

}
