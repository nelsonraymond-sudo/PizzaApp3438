package com.example.pizzaapp3438

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.squareup.picasso.Picasso

class AdapterTransaction(): RecyclerView.
    Adapter<AdapterTransaction.ViewHolderOrder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterTransaction.ViewHolderOrder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(
            R.layout.card_layout_order,
            parent,
            false
        )

        return ViewHolderOrder(cellForRow)
    }

    class ViewHolderOrder(v: View) : RecyclerView.ViewHolder(v) {
        val imgFotoMenu: ImageView
        val textNamaMenu: TextView
        val textIdMenu: TextView
        val textHargaMenu: TextView
        val btnLess: TextView
        val textQty: TextView
        val btnMore: TextView
        val context = v.context

        init {
            imgFotoMenu = v.findViewById(R.id.imageMenu)
            textNamaMenu = v.findViewById(R.id.textNamaMenu)
            textIdMenu = v.findViewById(R.id.textIdMenu)
            textHargaMenu = v.findViewById(R.id.textHargaMenu)
            textQty = v.findViewById(R.id.textLess)
            btnLess = v.findViewById(R.id.textQtyMenu)
            btnMore = v.findViewById(R.id.textMore)
        }
    }

    override fun onBindViewHolder(
        holder: ViewHolderOrder,
        position: Int
    ) {
        holder.textIdMenu.text = listId[position]
        holder.textNamaMenu.text = listName[position]
        holder.textHargaMenu.text = listPrice[position].toString()
        holder.textQty.text = listQty[position].toString()
        var url = "http://192.168.100.87/rest_apiXXX/gambar/" + listPicture[position]
        Picasso.get().load(url).into(holder.imgFotoMenu)
    }

    override fun getItemCount(): Int {
        return listId.size
    }

    companion object {
        var amount: Int = 0
        var listId = mutableListOf<String>()
        var listName = mutableListOf<String>()
        var listPrice = mutableListOf<Int>()
        var listPicture = mutableListOf<String>()
        var listQty = mutableListOf<Int>()
        var price: Int = 0
    }
}