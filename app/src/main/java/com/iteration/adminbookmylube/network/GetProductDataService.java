package com.iteration.adminbookmylube.network;

import com.iteration.adminbookmylube.model.BookingList;
import com.iteration.adminbookmylube.model.MessageLogin;
import com.iteration.adminbookmylube.model.Message;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetProductDataService {

    @FormUrlEncoded
    @POST("webservice/login.php")
    Call<MessageLogin> getLoginData(@Field("email") String email,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("webservice/admin_booking_list.php")
    Call<BookingList> getAdminBookingData(@Field("booking_date") String booking_date,
                                          @Field("booking_service_opt") String booking_service_opt,
                                          @Field("booking_status") String booking_status);

    @FormUrlEncoded
    @POST("webservice/conform_booking.php")
    Call<Message> getConformBookingData(@Field("booking_id") String booking_id,
                                        @Field("booking_status") String booking_status,
                                        @Field("booking_price") String booking_price,
                                        @Field("booking_remind_date") String booking_remind_date,
                                        @Field("booking_admin_comment") String booking_admin_comment);

}
