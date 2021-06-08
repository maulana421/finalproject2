package com.example.projectbatch2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.projectbatch2.databinding.ActivityDetailBarangBinding
import com.example.projectbatch2.fullAPI.APIService
import com.example.projectbatch2.fullAPI.ListRespon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Detail_barang_Activity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBarangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBarangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GetbyId()
        BtnDelete()
        ButtonBack()

        val id = intent.getIntExtra("id", 0)
        Log.d("id barang", "$id ")
    }
    private fun DeleteBarang(){
        APIService.APIEndPoint().DeleteById(intent.getIntExtra("id",1)).enqueue(object :
            Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful){
                    Toast.makeText(applicationContext,"Delete Data Berhasil", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println(t.message)
            }


        })
    }
    private fun BtnDelete(){
        binding.imgdelete.setOnClickListener {
            DeleteBarang()
            startActivity(Intent(this,MainActivity2::class.java))
            finish()
        }
    }

    private fun GetbyId(){
        APIService.APIEndPoint().GetById(intent.getIntExtra("id",0)).enqueue(object :
            Callback<ListRespon<listbarang>> {
            override fun onResponse(call: Call<ListRespon<listbarang>>, response: Response<ListRespon<listbarang>>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        ShowDetail(body.data)
                        Log.d("Data by id", "${body.data} ")
                    }
                }
            }

            override fun onFailure(call: Call<ListRespon<listbarang>>, t: Throwable) {
                Toast.makeText(applicationContext,t.message, Toast.LENGTH_SHORT).show()
            }
        })

    }


    private fun ShowDetail(post : List<listbarang>){
        binding.detailnama.text = post[0].nama
        binding.detailkode.text = post[0].kode.toString()
        binding.detailcreate.text = post[0].createdAt
        binding.detailupdate.text = post[0].updatedAt
    }

    private fun ButtonBack(){
        binding.back.setOnClickListener {
            startActivity(Intent(this,MainActivity2::class.java))
            finish()
        }
    }
}