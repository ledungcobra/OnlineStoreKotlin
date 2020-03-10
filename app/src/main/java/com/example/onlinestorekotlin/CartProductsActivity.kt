package com.example.onlinestorekotlin

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_cart_products.*
import java.lang.Exception

class CartProductsActivity : AppCompatActivity() {
    private lateinit var  temporaryProducts:MutableList<TempEProduct>
    private lateinit var rvAdapter:TempProductRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_products)

       temporaryProducts = mutableListOf()
       rvAdapter = TempProductRVAdapter(this,temporaryProducts)





        val stringURL = "http://192.168.1.7/OnlineStoreApp/join_2_tables.php?email=${Person.email}"

        val requestQ = Volley.newRequestQueue(this)
        val jsonAR = JsonArrayRequest(Request.Method.GET,stringURL,null,{
            response ->
            try {

                for(index in 0.until(response.length())){

                    val currentEProduct = response.getJSONObject(index)
                    temporaryProducts.add(TempEProduct(
                        id =currentEProduct.getInt("id"),
                        name = currentEProduct.getString("name"),
                        price = currentEProduct.getInt("price"),
                        brand = currentEProduct.getString("brand"),
                        picture = currentEProduct.getString("picture"),
                        amount = currentEProduct.getInt("amount")

                    ))


                }
                recyclerViewTempProducts.layoutManager = LinearLayoutManager(this)
                recyclerViewTempProducts.adapter = rvAdapter

            }catch (e:Exception){

                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle("Error")
                alertDialog.setMessage(e.message)
                alertDialog.create().show()

            }




        },{

            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Error")
            alertDialog.setMessage(it.message)
            alertDialog.create().show()

        })

        requestQ.add(jsonAR)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.cart_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when(item.itemId){

            R.id.continueShopping->{
                finish()
                startActivity(Intent(this,HomeScreen::class.java))
            }

            R.id.declineItem->{
                Person.addToCardProductId?.let {
                    id->

                    val stringURL = "http://192.168.1.7/OnlineStoreApp/delete_temporary_order.php?email=${Person.email}&product_id=$id"
                    val requestQ = Volley.newRequestQueue(this)
                    val stringRequest = StringRequest(Request.Method.GET,stringURL,{

                        if(it.equals("DONE")){

                            temporaryProducts.removeIf {
                                it.id == Person.addToCardProductId
                            }
                            Person.addToCardProductId = null
                            rvAdapter.notifyDataSetChanged()

                        }


                    },{

                    })

                    requestQ.add(stringRequest)

                }


            }

            R.id.verifyItem->{

                Person.addToCardProductId?.let {

                    Person.addToCardProductId = null

                }

            }

        }

        return super.onOptionsItemSelected(item)
    }
}
