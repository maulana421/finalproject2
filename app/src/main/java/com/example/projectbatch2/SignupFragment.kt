package com.example.projectbatch2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.projectbatch2.databinding.FragmentSignupBinding
import com.example.projectbatch2.fullAPI.APIService
import com.example.projectbatch2.fullAPI.SingleRespon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignupFragment : Fragment() {
    private lateinit var binding : FragmentSignupBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding  = FragmentSignupBinding.inflate(layoutInflater)
        BtnSIgnUp()
        // Inflate the layout for this fragment
        return binding.root
    }


    private fun Regist(){
        val name = binding.ETNameSignUp.text.toString()
        val username = binding.ETUsernameSignUp.text.toString()
        val email = binding.ETEmailSignUp.text.toString()
        val password = binding.ETPasswordSignUp.text.toString()

        APIService.APIEndPoint().SignUp(name, username, email, password).enqueue(object :
            Callback<SingleRespon<Model>> {
            override fun onResponse(
                call: Call<SingleRespon<Model>>,
                response: Response<SingleRespon<Model>>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null){
                        Toast.makeText(activity,"Sign Up Success", Toast.LENGTH_SHORT).show()
                        Log.d("Register", "onResponse: ${body.data}")
                    }
                }
            }

            override fun onFailure(call: Call<SingleRespon<Model>>, t: Throwable) {
                println(t.message)
            }
        })
    }

    private fun BtnSIgnUp(){
        binding.ButtonSignUp.setOnClickListener {
            Regist()
        }

    }
}