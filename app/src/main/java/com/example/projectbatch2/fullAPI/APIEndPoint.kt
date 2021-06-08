package com.example.projectbatch2.fullAPI

import com.example.projectbatch2.Model
import com.example.projectbatch2.listbarang
import retrofit2.Call
import retrofit2.http.*

interface APIEndPoint {


    @FormUrlEncoded
    @POST("auth/sign-up")
    fun SignUp(@Field("name")name : String,
               @Field("username")username : String,
               @Field("email")email : String,
               @Field("password")password : String
    ): Call<SingleRespon<Model>>

    @FormUrlEncoded
    @POST("auth/sign-in")
    fun SignIn(@Field("username")username: String,
               @Field("password")password: String): Call<SingleRespon<Model>>

    //RetrofitCRUD
    @GET("barang")
    fun GetBarang(): Call<ListRespon<listbarang>>

    @GET("barang/{id}")
    fun GetById(@Path("id")id : Int): Call<ListRespon<listbarang>>

    @DELETE("barang/{id}")
    fun DeleteById(@Path("id")id : Int): Call<Void>

    @FormUrlEncoded
    @POST("barang/")
    fun PostBarang(@Field("nama")nama : String,
                   @Field("kode")kode :Int) : Call<SingleRespon<listbarang>>

    @FormUrlEncoded
    @PUT("barang/{id}")
    fun UpdateBarang(@Path("id")id:Int,
                     @Field("nama")nama : String,
                     @Field("kode")kode :Int) : Call<SingleRespon<listbarang>>


}
data class ListRespon<T>(
    var msg : String,
    var status : Int,
    var data : List<T>
)
data class SingleRespon<T>(
    var msg : String,
    var status : Int,
    var data :T
)