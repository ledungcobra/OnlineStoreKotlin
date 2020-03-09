package com.example.onlinestorekotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.fragment_amount.*

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

        btnAddToCard.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack("TAG", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }


        return fragmentView
    }

}
