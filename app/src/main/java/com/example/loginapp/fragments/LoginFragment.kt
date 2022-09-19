package com.example.loginapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.loginapp.R
import com.example.loginapp.databinding.FragmentLoginBinding
import kotlinx.coroutines.flow.combine

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var sharedPref: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedPref = activity?.getSharedPreferences("ACCOUNT", Context.MODE_PRIVATE)!!
        val usernamePref = sharedPref.getString("username", "")
        val fullNamePref = sharedPref.getString("full_name", "")
        val passwordPref = sharedPref.getString("password", "")
        Log.d("Login", "Username: $usernamePref")
        Log.d("Login", "Password: $passwordPref")
        Log.d("Login", "Full Name: $fullNamePref")
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            if(isExist(username)){
                if(isPasswordCorrect(password)){
                    Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_homeFragment)
                }else{
                    errorToast("Password Anda Salah")
                }
            }else{
                errorToast("Username tidak terdaftar")
            }
        }
        binding.tvRegister.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }


    private fun isExist(username: String):Boolean{
        val usernameSharedPref = sharedPref.getString("username", "")
        if (usernameSharedPref != null) {
            if(usernameSharedPref.isBlank()){
                return false
            }else{
                if(usernameSharedPref == username){
                    return true
                }
            }
        }
        return false
    }

    private fun isPasswordCorrect(password:String):Boolean{
        val passwordSharedPref = sharedPref.getString("password", "")
        if (passwordSharedPref != null){
            if(passwordSharedPref.isBlank()){
                return false
            }else{
                if(passwordSharedPref == password){
                    return true
                }
            }
        }
        return false
    }

    private fun errorToast(msg: String){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

}