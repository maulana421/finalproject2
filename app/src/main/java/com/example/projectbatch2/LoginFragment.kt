package com.example.projectbatch2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.projectbatch2.databinding.FragmentLoginBinding
import com.example.projectbatch2.fullAPI.APIService
import com.example.projectbatch2.fullAPI.Constant
import com.example.projectbatch2.fullAPI.SingleRespon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        BtnSignIn()
        IsLogin()

        return binding.root

    }

    private fun Login(){
        val username = binding.ETUsername.text.toString()
        val password = binding.ETPassword.text.toString()
        if (binding.ETUsername.text.isNullOrEmpty() && binding.ETPassword.text.isNullOrEmpty()){
            Toast.makeText(activity,"Username dan Password Harus diisi", Toast.LENGTH_SHORT).show()
        }else{
            APIService.APIEndPoint().SignIn(username, password).enqueue(object :
                Callback<SingleRespon<Model>> {
                override fun onResponse(
                    call: Call<SingleRespon<Model>>,
                    response: Response<SingleRespon<Model>>
                ) {
                    if(response.isSuccessful){
                        val body = response.body()
                        if(body!= null){
                            Constant.SetUsername(activity!!,body.data.username)
                            Constant.SetNama(activity!!,body.data.name)
                            Constant.SetToken(activity!!,body.data.token)
                            Toast.makeText(activity,"Hai ${body.data.username}", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(activity,MainActivity2::class.java).also {
                                activity!!.finish()
                            })

                        }
                    }else{
                        Toast.makeText(activity,"Login Failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<SingleRespon<Model>>, t: Throwable) {
                    println(t.message)
                }
            })
        }

    }
    private fun BtnSignIn(){
        binding.ButtonSignIn.setOnClickListener {
            Login()

        }
    }
    private fun IsLogin(){
        val login = activity?.let { Constant.GetToken(it) }
        if(!login.equals("LOGIN")){
            startActivity(Intent(activity,MainActivity2::class.java).also {
                requireActivity().finish()
            })
        }
    }
}