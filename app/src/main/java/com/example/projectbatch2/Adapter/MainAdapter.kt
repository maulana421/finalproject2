package com.example.projectbatch2.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectbatch2.databinding.ListbarangBinding
import com.example.projectbatch2.listbarang

class MainAdapter(private var user : List<listbarang>, val listener : MainAdapter.OnClick):
    RecyclerView.Adapter<MainAdapter.UserViewHolder>() {
    inner class UserViewHolder(val binding : ListbarangBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ListbarangBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.apply {
            Barang.text = user[position].nama
            Barang.setOnClickListener {
                listener.ListClick(user[position])
            }
            IconEdit.setOnClickListener {
                listener.ImageClick(user[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return user.size
    }
    interface OnClick{
        fun ListClick(post : listbarang)
        fun ImageClick(post : listbarang)
    }


}