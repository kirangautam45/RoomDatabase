package com.kiran.roomdatabase25c

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.kiran.roomdatabase25c.db.StudentDB
import com.kiran.roomdatabase25c.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private lateinit var etUsername: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var tvRegister: TextView
    private lateinit var btnLogin: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvRegister = findViewById(R.id.tvRegister)

        tvRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
        btnLogin.setOnClickListener{
            login()
        }
    }

    private fun login(){
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()

        var user: User? = null;
        CoroutineScope(Dispatchers.IO).launch {
            StudentDB.getInstance(this@LoginActivity)
                .getUserDAO().loginUser(username,password)

            if(user==null){
                withContext(Main)
                {
                    Toast.makeText(this@LoginActivity, "Invalid ", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                startActivity(Intent(this@LoginActivity,DashboardActivity::class.java))
            }
        }

    }
}