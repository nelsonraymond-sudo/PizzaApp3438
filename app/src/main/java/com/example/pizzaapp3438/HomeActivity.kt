package com.example.pizzaapp3438

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class HomeActivity : AppCompatActivity() {
    // fc replace fragment
    private fun  replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTrx = fragmentManager.beginTransaction()
        fragmentTrx.replace(R.id.fragmentContainerView, fragment)
        fragmentTrx.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //instance
        val txtAccount: TextView = findViewById(R.id.textViewMenuAccount)
        val txtMenu = findViewById<TextView>(R.id.textViewMenu)
        val txtTransaction = findViewById<TextView>(R.id.textViewMenuTransaction)
        val txtReport = findViewById<TextView>(R.id.textViewMenuReport)
        //event menu account click
        txtAccount.setOnClickListener {
            replaceFragment(AccountFragment())
        }
        //event menu  click
        txtMenu.setOnClickListener {
            replaceFragment(MenuFragment())
        }
        //event menu transaction click
        txtTransaction.setOnClickListener {
            replaceFragment(TransactionFragment())
        }
        //event menu report click
        txtReport.setOnClickListener {
            replaceFragment(ReportFragment())
        }
    }
}