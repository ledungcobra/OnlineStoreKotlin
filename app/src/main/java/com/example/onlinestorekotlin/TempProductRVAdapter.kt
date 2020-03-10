package com.example.onlinestorekotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rw_eproduct_row.view.*

class TempProductRVAdapter(
    private val context: Context,
    private val listEProducts: MutableList<TempEProduct>
) :
    RecyclerView.Adapter<TempProductRVAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun initUIElements(id: Int, name: String, price: Int, image: String,amount:Int) {

            val imgEProduct = this.itemView.findViewById<ImageView>(R.id.rv_item_temp_imgEProduct)
            val txtEProductPrice = this.itemView.findViewById<TextView>(R.id.rv_item_temp_txtPrice)
            val txtEProductName = this.itemView.findViewById<TextView>(R.id.rv_item_temp_txtEProductName)
            val txtEProductAmount = this.itemView.findViewById<TextView>(R.id.rv_item_temp_txtAmount)

            txtEProductName.text = name
            txtEProductPrice.text = price.toString()
            val stringURL = "http://192.168.1.7/OnlineStoreApp/images/" + image
            val imageURL = stringURL.replace(" ", "%20")
            Picasso.get().load(imageURL).into(imgEProduct)
            itemView.rv_item_txtId.text = id.toString()
            txtEProductAmount.text = amount.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val rowView =
            LayoutInflater.from(context).inflate(R.layout.rv_row_temp_product, parent, false)
        return MyViewHolder(rowView)

    }

    override fun getItemCount(): Int {
        return listEProducts.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.initUIElements(listEProducts[position].id,listEProducts[position].name,listEProducts[position].price,listEProducts[position].picture,listEProducts[position].amount)


    }

}