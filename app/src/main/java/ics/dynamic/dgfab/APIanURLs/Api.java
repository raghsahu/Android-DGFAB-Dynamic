package ics.dynamic.dgfab.APIanURLs;

import ics.dynamic.dgfab.AllParsings.GET_Services;
import ics.dynamic.dgfab.AllParsings.Registration_only;
import ics.dynamic.dgfab.AllParsings.Registration_only_data;
import ics.dynamic.dgfab.AllParsings.Type_Sub_Users;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
    Call<Registration_only> REGISTRATION_ONLY_CALL(
            @Field("user_type") String user_type,
            @Field("name") String name,
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("address") String address,
            @Field("company_name") String company_name,
            @Field("password") String password,
            @Field("sub_type") String sub_type
    );
 @FormUrlEncoded
    @POST(REtroURls.Registration)
    Call<GET_Services> Ragistration(
            @Field("type_id") String type_of_user
    );

}
