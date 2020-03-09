package com.example.onlinestorekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home_screen.*
import java.util.ArrayList

class HomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val stringURL = "http://192.168.1.7/OnlineStoreApp/fetch_brands.php"
        val requestQ = Volley.newRequestQueue(this)
        val brandsList = ArrayList<String>()
        val adapter = ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,brandsList)

        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET,stringURL,null,{

            for(brandIndex in 0.until(it.length())){

                brandsList.add(it.getJSONObject(brandIndex).getString("brand"))

            }
            brandsListView.adapter = adapter

        },{

            Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()

        })

        requestQ.add(jsonArrayRequest)
        
        brandsListView.setOnItemClickListener { parent, view, position, id ->

            val tappedBrand = brandsList.get(position)
            val intent = Intent(this@HomeScreen,FetchEProductsActivity::class.java)
            intent.putExtra("tappedBrand",tappedBrand)
            startActivity(intent)


        }


    }
}
