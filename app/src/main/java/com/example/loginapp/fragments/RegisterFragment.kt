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
import androidx.navigation.Navigation
import com.example.loginapp.R
import com.example.loginapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var sharedPref: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedPref = activity?.getSharedPreferences("ACCOUNT",Context.MODE_PRIVATE)!!

        binding.btnRegister.setOnClickListener {
            val username = binding.etUsernameReg.text.toString()
            val fullName = binding.etFullNameReg.text.toString()
            val password = binding.etPasswordReg.text.toString()
            val confirmPassword = binding.etConfPassword.text.toString()
            if(password != confirmPassword){
                errorToast("Password dan Konfirmasi Password Tidak Sama!")
            }else{
                addToSharedPref(username, fullName, password)
                Navigation.findNavController(binding.root).navigate(R.id.action_registerFragment_to_homeFragment2)
            }
        }
    }

    private fun errorToast(msg: String){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    private fun addToSharedPref(username: String, fullName: String, pass: String){
        val addData = sharedPref.edit()
        Log.d("Register", "Username: $username")
        Log.d("Register", "Password: $pass")
        Log.d("Register", "Full Name: $fullName")
        addData.putString("username", username)
        addData.putString("full_name", fullName)
        addData.putString("password", pass)
        addData.apply()
        Toast.makeText(context,"Registrasi Berhasil", Toast.LENGTH_SHORT).show()
    }

}