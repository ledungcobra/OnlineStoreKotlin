package com.example.onlinestorekotlin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rw_eproduct_row.view.*

open class RVAdapter(private val context: Context, private val listEProducts: ArrayList<EProduct>): RecyclerView.Adapter<RVAdapter.MyViewHolder>() {
    open var isShowAddToCart = true

    open inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        open fun initUIElements(id: Int, name: String, price: Int, image: String) {

            val imgEProduct = this.itemView.findViewById<ImageView>(R.id.rv_item_imgEProduct)
            val txtEProductPrice = this.itemView.findViewById<TextView>(R.id.rv_item_txtPrice)
            val txtEProductName = this.itemView.findViewById<TextView>(R.id.rv_item_txtEProductName)

            txtEProductName.text = name
            txtEProductPrice.text = price.toString()
            val stringURL = "http://192.168.1.7/OnlineStoreApp/images/" + image
            val imageURL = stringURL.replace(" ", "%20")
            Picasso.get().load(imageURL).into(imgEProduct)
            itemView.rv_item_txtId.text = id.toString()

            itemView.imgAdd.visibility = if (isShowAddToCart) View.VISIBLE else View.GONE
            if (isShowAddToCart) {

                itemView.imgAdd.setOnClickListener {

                    val amountFragment = AmountFragment()

                    val bundle = Bundle()
                    bundle.putInt("product_id", id)
                    amountFragment.arguments = bundle
                    val fragmentManager =
                        (itemView.context as FetchEProductsActivity).supportFragmentManager
                    fragmentManager.beginTransaction()
                        .replace(R.id.addToCart, amountFragment).addToBackStack("TAG")
                        .commit()


                }

            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.rw_eproduct_row, parent, false) as View




        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listEProducts.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.initUIElements(
            listEProducts[position].id,
            listEProducts[position].name,
            listEProducts[position].price,
            listEProducts[position].picture
        )


    }
}