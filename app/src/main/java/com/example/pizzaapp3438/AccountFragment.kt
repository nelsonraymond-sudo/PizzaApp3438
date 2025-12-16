package com.example.pizzaapp3438

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import com.example.pizzaapp3438.LoginActivity.Companion.email
// Note: You likely need to import LoginActivity.Companion.name, .level, etc. if you are using them
// or defined them locally. I've left the logic as is based on your snippet.
import com.example.pizzaapp3438.client.RetrofitClient
import com.example.pizzaapp3438.response.account.Data // Ensure this import is here
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AccountFragment : Fragment() {

    private lateinit var notificationManager: NotificationManagerCompat

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notificationManager = NotificationManagerCompat.from(view.context)

        val txtNama = view.findViewById<EditText>(R.id.txtNama)
        val txtLevel = view.findViewById<EditText>(R.id.txtLevel)
        val txtPassword = view.findViewById<EditText>(R.id.txtPassword)
        val btnSave = view.findViewById<Button>(R.id.buttonSave)

        // Assuming txtEmail is declared somewhere or meant to be the 'email' variable
        // If txtEmail doesn't exist in layout, remove the line "email = txtEmail.text.toString()" below

        btnSave.setOnClickListener {
            RetrofitClient.instance.putAccount(
                email,
                txtNama.text.toString(),
                txtLevel.text.toString(),
                txtPassword.text.toString()
            ).enqueue(object : Callback<Data> { // CHANGED: AccountResponse -> Data
                override fun onResponse(p0: Call<Data>, p1: Response<Data>) { // CHANGED types
                    val accountData = p1.body()
                    if (accountData != null) {
                        // Assuming 'Data' class has a 'message' field based on your previous code usage.
                        // If 'Data' does not have 'message', remove this Toast or use a different field.
                        // Toast.makeText(view.context, accountData.message, Toast.LENGTH_SHORT).show()

                        Toast.makeText(view.context, "Update Successful", Toast.LENGTH_SHORT).show()

                        // Update global variables if needed
                        // email = ...
                        // name = txtNama.text.toString()
                        // level = txtLevel.text.toString()
                        // password = txtPassword.text.toString()
                    }
                }

                override fun onFailure(p0: Call<Data>, p1: Throwable) { // CHANGED types
                    Toast.makeText(view.context, p1.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
