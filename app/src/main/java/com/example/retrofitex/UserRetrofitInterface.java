package com.example.retrofitex;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserRetrofitInterface {

    @GET("user")
    Call<UserDTO> getUser();

    @POST("save-user")
    Call<ResponseBody> saveUser(@Body UserDTO jsonUser);

    @POST("finduser")
    Call<ResponseBody> findUser(@Body String username);

    @GET("find_list")
    Call<List<ListItem>> findList();
}
