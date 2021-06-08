package com.example.projectbatch2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectbatch2.Adapter.MainAdapter
import com.example.projectbatch2.databinding.ActivityMain2Binding
import com.example.projectbatch2.fullAPI.APIService
import com.example.projectbatch2.fullAPI.ListRespon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding : ActivityMain2Binding
    private lateinit var mainAdapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        BotNav()
    }
    override fun onResume() {
        super.onResume()
        GetBarang()

    }
    private fun BotNav(){
        binding.Botnav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> startActivity(Intent(this,MainActivity2::class.java))
                R.id.add -> startActivity(Intent(this,MainActivity_add::class.java))
                R.id.akun-> startActivity(Intent(this,MainActivity_account::class.java))
                R.id.fav-> startActivity(Intent(this,MainActivity_fav::class.java))
            }
            true
        }
    }
    private fun GetBarang(){
        APIService.APIEndPoint().GetBarang().enqueue(object :
            Callback<ListRespon<listbarang>> {
            override fun onResponse(call: Call<ListRespon<listbarang>>, response: Response<ListRespon<listbarang>>) {
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null){
                        ShowRcy(body.data)
                        Toast.makeText(applicationContext,"Get Data Success", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ListRespon<listbarang>>, t: Throwable) {
                println(t.message)
            }


        })
    }
    private fun ShowRcy(model: List<listbarang>){
        mainAdapter = MainAdapter(model,object : MainAdapter.OnClick{
            override fun ListClick(post: listbarang) {
                startActivity(Intent(this@MainActivity2,Detail_barang_Activity::class.java).apply {
                    putExtra("id",post.id)
                })


            }

            override fun ImageClick(post: listbarang) {
                startActivity(Intent(this@MainActivity2,MainActivity_add::class.java).apply {
                    putExtra("id",post.id)
                    putExtra("nama",post.nama)
                    putExtra("kode",post.kode.toString())
                })
            }

        })
        binding.rcyview.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity2)
        }

    }
}