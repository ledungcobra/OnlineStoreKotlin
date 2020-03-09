package com.example.onlinestorekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_fetch_e_products.*

class FetchEProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_e_products)


        val listEProducts = ArrayList<EProduct>()
        val rvAdapter = RVAdapter(this,listEProducts)
        val linearManager = LinearLayoutManager(this)


        recyclerViewEProducts.adapter = rvAdapter
        recyclerViewEProducts.layoutManager = linearManager


        val selectedBrand = intent.getStringExtra("tappedBrand")
        fetch_products_txtTitle.text = fetch_products_txtTitle.text.toString() + selectedBrand

        val stringURL = "http://192.168.1.7/OnlineStoreApp/fetch_specific_brand.php?brand=$selectedBrand"
        val requestQ = Volley.newRequestQueue(this@FetchEProductsActivity)
        val jsonAR = JsonArrayRequest(Request.Method.GET,stringURL,null,{
                response->

            for(index in 0.until(response.length())){

                val currentEProduct = response.getJSONObject(index)
                listEProducts.add(EProduct(
                    id =currentEProduct.getInt("id"),
                    name = currentEProduct.getString("name"),
                    price = currentEProduct.getInt("price"),
                    brand = currentEProduct.getString("brand"),
                    picture = currentEProduct.getString("picture")

                ))


            }
            rvAdapter.notifyDataSetChanged()


        },{

        })
        requestQ.add(jsonAR)





    }
}
