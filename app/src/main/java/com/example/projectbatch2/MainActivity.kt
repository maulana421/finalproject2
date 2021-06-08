package com.example.projectbatch2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectbatch2.Adapter.TabNavAdapter
import com.example.projectbatch2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Tabnav()
    }
    private fun Tabnav(){
        val FragmentAdapter = TabNavAdapter(supportFragmentManager)
        binding.page.adapter = FragmentAdapter
        binding.tablayout.setupWithViewPager(binding.page)
    }
}