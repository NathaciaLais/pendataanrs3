package Nathacia.uas.pendataanrs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {
    @FormUrlEncoded
    @POST("auth/login")
    Call<ValueData<User>>login(@Field("username")String username,
                @Field("password")String password);

    @FormUrlEncoded
    @POST("auth/register")
    Call<ValueData<User>>register(@Field("username")String username,
                                  @Field("password")String password);

    @GET("pendataanrs")
    Call<ValueData<List<Daftar>>> getPendataanrs();


    @FormUrlEncoded
    @POST("pendataanrs")
    Call<ValueNulData>addUnggah(@Field("nama")String namarumahsakit,
                                 @Field("alamat")String alamatrumahsakit,
                                 @Field("deskripsi")String deskripsi,
                                 @Field("user_id")String user_id);

    @FormUrlEncoded
    @PUT("pendataanrs")
    Call<ValueNulData>updateShoes(@Field("id")String id,
                                 @Field("nama")String namarumahsakit,
                                 @Field("alamat")String alamatrumahsakit,
                                 @Field("deskripsi")String deskripsi);

    @DELETE("pendataanrs/{id}")
    Call<ValueNulData> deletependataanrs(@Path("id")String id);


}
