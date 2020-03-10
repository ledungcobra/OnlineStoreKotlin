package com.example.onlinestorekotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_sign_up.*

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        sign_up_layout_btnLogin.setOnClickListener {
            finish()
        }

        sign_up_layout_btnSignUp.setOnClickListener {
            val alertBuilder = AlertDialog.Builder(this)
            if(!sign_up_layout_edtCFPassword.text.toString()
                    .equals(sign_up_layout_edtPassword.text.toString())){


                alertBuilder.setTitle("Alert")
                alertBuilder.setMessage("Password mismatch")
                alertBuilder.create().show()

            }else{


                val serverURL = "http://192.168.1.7/OnlineStoreApp/join_new_user.php" +
                        "?username=${sign_up_layout_edtUsername.text}&" +
                        "email=${sign_up_layout_edtEmail.text}&" +
                        "password=${sign_up_layout_edtPassword.text}"
                val requestQ = Volley.newRequestQueue(this)
                val stringRequest = StringRequest(Request.Method.GET,serverURL,{

                    response->
                    alertBuilder.setMessage(response.toString())

                    alertBuilder.create().show()
                    if(response.toString().equals("Sign Up successfully")){

                        Person.email = sign_up_layout_edtEmail.text.toString()

                        finish()
                        startActivity(Intent(this@SignUpActivity,HomeScreen::class.java))

                    }


                },{
                    error ->
                    alertBuilder.setMessage(error.toString())
                    alertBuilder.create().show()
                })
                requestQ.add(stringRequest)

            }
        }

    }


    fun hideKeyboard(v: View){
        v.hideKeyboard()

    }

}
