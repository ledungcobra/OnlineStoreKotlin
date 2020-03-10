package com.example.onlinestorekotlin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_amount.*
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class AmountFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_amount, container, false)


        val btnAddToCard = fragmentView.findViewById<ImageView>(R.id.addAmountToCart)
        val edtAmount = fragmentView.findViewById<EditText>(R.id.edtAmount)

        val id = arguments?.getInt("product_id")


        btnAddToCard.setOnClickListener {
            try {
                if (!edtAmount.text.equals("")) {

                    Person.addToCardProductId = id;

                    val stringURL = "http://192.168.1.7/OnlineStoreApp/add_to_temporary_order.php" +
                            "?product_id=$id" +
                            "&email=${Person.email}" +
                            "&amount=${edtAmount.text}"
                    val requestQ:RequestQueue? = Volley.newRequestQueue(activity)
                    val stringRequest = StringRequest(Request.Method.GET, stringURL, {

                        Toast.makeText(activity,it,Toast.LENGTH_SHORT).show()
                        activity?.supportFragmentManager?.popBackStack(
                            "TAG",
                            FragmentManager.POP_BACK_STACK_INCLUSIVE
                        )
                        startActivity(Intent(activity,CartProductsActivity::class.java))

                    }, {

                    })

                    requestQ?.add(stringRequest)




                } else {


                }
            } catch (e: Exception) {
                Toast.makeText(activity, e.message, Toast.LENGTH_SHORT)
                    .show()


            }

        }


        return fragmentView
    }

}
