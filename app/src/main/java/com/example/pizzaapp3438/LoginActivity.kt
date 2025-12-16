package com.example.pizzaapp3438

import android.content.Intent
import android.os.Bundle
import android.telecom.Call
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pizzaapp3438.client.RetrofitClient
import com.example.pizzaapp3438.response.account.LoginResponse
import okhttp3.Callback
import okhttp3.Response
import kotlin.toString

class LoginActivity : AppCompatActivity() {
    companion object {
        var name = "Nelson Raymond"
        var email = "nelsonraymond@students.amikom.ac.id"
        var password = "nelson123"
        var level = "CEO"
    }
    //event button Login click
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //login validation -> if true call activity Account
        val txtUsername: EditText = findViewById(R.id.editTextText3)
        val txtPassword: EditText = findViewById(R.id.editTextText4)
        val btnLogin: Button = findViewById(R.id.buttonLogin)
        btnLogin.setOnClickListener {
            var user = txtUsername.text.toString().trim()
            var pwd = txtPassword.text.toString().trim()
            if(user.isEmpty()){
                txtUsername.error = "Email required"
                txtUsername.requestFocus()
                return@setOnClickListener
            }
            if(pwd.isEmpty()){
                txtPassword.error = "Password required"
                txtPassword.requestFocus()
                return@setOnClickListener
            }
            // ... inside btnLogin.setOnClickListener ...

            RetrofitClient.instance.postLogin(user, pwd).enqueue(
                object : retrofit2.Callback<LoginResponse> {
                    override fun onResponse(
                        call: retrofit2.Call<LoginResponse>,
                        response: retrofit2.Response<LoginResponse>
                    ) {
                        val account = response.body()
                        if (account?.success == true) {
                            Toast.makeText(this@LoginActivity,
                                account.message.toString(), Toast.LENGTH_SHORT).show()
                            val intentLogin = Intent(this@LoginActivity, AccountActivity::class.java)
                            startActivity(intentLogin)
                            email = account.data.username
                            name = account.data.name
                            level = account.data.level
                            password = account.data.password
                        } else {
                            Toast.makeText(this@LoginActivity,
                                account?.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: retrofit2.Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }
}