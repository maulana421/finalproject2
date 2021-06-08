package com.example.projectbatch2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projectbatch2.databinding.ActivityMainAddBinding
import com.example.projectbatch2.fullAPI.APIService
import com.example.projectbatch2.fullAPI.SingleRespon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity_add : AppCompatActivity() {
    private lateinit var binding : ActivityMainAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ButtonAdd()
        getdata()
        ButtonUpdate()

    }
    private fun AddBarang(){
        val nama = binding.ETNamaBarang.text.toString()
        val kode = binding.ETKode.text.toString()

        APIService.APIEndPoint().PostBarang(nama,kode.toInt()).enqueue(object :
            Callback<SingleRespon<listbarang>> {
            override fun onResponse(call: Call<SingleRespon<listbarang>>, response: Response<SingleRespon<listbarang>>) {
                if(response.isSuccessful){
                    val body = response.body()
                    Toast.makeText(applicationContext,"Post Barang Berhasil", Toast.LENGTH_SHORT).show()
                    println("Berhasil Post Barang "+body)
                }else{
                    Toast.makeText(applicationContext,"Error Post Barang", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SingleRespon<listbarang>>, t: Throwable) {
                Toast.makeText(applicationContext,t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun ButtonAdd(){
        binding.ButtonSave.setOnClickListener {
            AddBarang()
            startActivity(Intent(this,MainActivity2::class.java))
            finish()
        }
    }

    private fun getdata(){

        binding.ETNamaBarang.setText(intent.getStringExtra("nama"))
        binding.ETKode.setText(intent.getStringExtra("kode"))
    }
    private fun UpdateBarang(){
        val id = intent.getIntExtra("id",1)
        val nama = binding.ETNamaBarang.text.toString()
        val kode = binding.ETKode.text.toString().toInt()
        APIService.APIEndPoint().UpdateBarang(id, nama, kode).enqueue(object :
            Callback<SingleRespon<listbarang>> {
            override fun onResponse(call: Call<SingleRespon<listbarang>>, response: Response<SingleRespon<listbarang>>) {
                if(response.isSuccessful){
                    val body = response
                    Toast.makeText(applicationContext,"Update Barang berhasil", Toast.LENGTH_SHORT).show()
                    println("Berhasil Update Barang "+body)
                }
            }

            override fun onFailure(call: Call<SingleRespon<listbarang>>, t: Throwable) {
                println(t.message)
            }
        })
    }
    private fun ButtonUpdate(){
        binding.ButtonnUpdate.setOnClickListener {
            UpdateBarang()
            startActivity(Intent(this,MainActivity2::class.java))
            finish()
        }
    }
}