package com.example.projectbatch2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectbatch2.databinding.ActivityMainAccountBinding
import com.example.projectbatch2.fullAPI.Constant
import kotlinx.android.synthetic.main.activity_main_account.*

class MainActivity_account : AppCompatActivity() {
    private lateinit var binding: ActivityMainAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Logout()
        GetData()
    }
    private fun Logout(){
        binding.BtnLogout.setOnClickListener {
            Constant.ClearToken(this)
            Constant.ClearNama(this)
            Constant.ClearUsername(this)
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
    private fun GetData(){
        binding.TvNama.text = "Welcome "+ Constant.GetNama(this)
        binding.TVUsername.text = "Username : " +Constant.GetUsername(this)

    }
}