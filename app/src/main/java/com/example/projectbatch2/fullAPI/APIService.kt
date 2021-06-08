package com.example.projectbatch2.fullAPI

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIService {
    companion object{
        private var retrofit: Retrofit? = null
        private var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        private fun getClient(): Retrofit {
            return if (retrofit == null){
                retrofit = Retrofit.Builder().baseUrl(Constant.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                retrofit!!
            }else{
                retrofit!!
            }
        }
        fun APIEndPoint() : APIEndPoint = getClient().create(APIEndPoint::class.java)
    }

}
class Constant{
    companion object{
        const val BASE_URL = "https://apibarang.herokuapp.com/"

        fun SetToken(context: Context, Token : String?){
            val token = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            token.edit().apply {
                putString("TOKEN",Token)
                apply()
            }
        }
        fun GetToken(context: Context):String{
            val sharedpref = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            val Token = sharedpref.getString("TOKEN","LOGIN")
            return Token!!
        }
        fun ClearToken(context: Context){
            val Delete = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            Delete.edit().clear().apply()
        }

        fun SetNama(context: Context, Nama :String){
            val nama = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            nama.edit().apply {
                putString("Name",Nama)
                apply()
            }
        }
        fun GetNama(context: Context):String?{
            val name = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            val nameregist = name.getString("Name","NAMA")
            return nameregist
        }
        fun ClearNama(context: Context){
            val delete = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            delete.edit().clear().apply()
        }

        fun SetUsername(context: Context, Username :String){
            val username = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            username.edit().apply {
                putString("Username",Username)
                apply()
            }
        }
        fun GetUsername(context: Context):String?{
            val name = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            val nameregist = name.getString("Username","User")
            return nameregist
        }
        fun ClearUsername(context: Context){
            val delete = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            delete.edit().clear().apply()
        }
    }
}