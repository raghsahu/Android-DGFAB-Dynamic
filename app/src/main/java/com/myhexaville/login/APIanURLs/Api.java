package com.myhexaville.login.APIanURLs;

import com.myhexaville.login.AllParsings.GET_Services;
import com.myhexaville.login.AllParsings.Type_Sub_Users;

import java.io.File;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST(REtroURls.Getsubusertype)
    Call<Type_Sub_Users> TYPE_SUB_USERS_CALL(
            @Field("type_id") String type_of_user
    );

    @FormUrlEncoded
    @POST(REtroURls.Get_Services)
    Call<GET_Services> Get_ServicesUsersCall(
            @Field("type_id") String type_of_user
    );
 @FormUrlEncoded
    @POST(REtroURls.Registration)
    Call<GET_Services> Ragistration(
            @Field("type_id") String type_of_user
    );

}
