package com.example.loginapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.loginapp.R
import com.example.loginapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedPref = activity?.getSharedPreferences("ACCOUNT",Context.MODE_PRIVATE)!!
        val fullName = sharedPref.getString("full_name", "")
        binding.tvFullName.text = fullName
        val usernamePref = sharedPref.getString("username", "")
        val fullNamePref = sharedPref.getString("full_name", "")
        val passwordPref = sharedPref.getString("password", "")
        Log.d("Home", "Username: $usernamePref")
        Log.d("Home", "Password: $passwordPref")
        Log.d("Home", "Full Name: $fullNamePref")
        binding.btnLogout.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_loginFragment2)
        }
    }
}