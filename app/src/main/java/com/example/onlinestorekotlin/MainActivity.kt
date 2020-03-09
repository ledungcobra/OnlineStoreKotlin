package com.example.onlinestorekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_btnSignUp.setOnClickListener {

            startActivity(Intent(this@MainActivity, SignUpActivity::class.java))

        }

        activity_main_btnLogin.setOnClickListener {

            val alertBuilder = AlertDialog.Builder(this)
            alertBuilder.setTitle("Alert")
            if (activity_main_edtLoginEmail.text.equals("") || activity_main_edtLoginPassword.text.equals(
                    "")
            ) {

                alertBuilder.setMessage("Please check your email or password again")
                alertBuilder.create().show()
                return@setOnClickListener

            }
            val stringURL =
                "http://192.168.1.7/OnlineStoreApp/login_user.php" + "?email=${activity_main_edtLoginEmail.text}&" +
                        "password=${activity_main_edtLoginPassword.text}"
            val requestQ = Volley.newRequestQueue(this)
            val stringRequest = StringRequest(Request.Method.GET, stringURL, {

                alertBuilder.setMessage(it.toString())
                alertBuilder.create().show()
                if(it.toString().equals("Login successfully")){

                    Person.email = activity_main_edtLoginEmail.text.toString()
                    Person.password = activity_main_edtLoginPassword.text.toString()
                    finish()
                    startActivity(Intent(this@MainActivity,HomeScreen::class.java))

                }

            }, {

                alertBuilder.setMessage(it.toString())
                alertBuilder.create().show()

            })
            requestQ.add(stringRequest)

        }

    }

    fun hideKeyboard(v: View) {
        v.hideKeyboard()

    }

}

