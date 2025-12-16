package com.example.pizzaapp3438

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaapp3438.response.food.FoodResponse
import com.squareup.picasso.Picasso

class AdapterMenu(private val listMenu: ArrayList<FoodResponse>): RecyclerView.Adapter<AdapterMenu.ViewHolder>() {

    inner class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val textIdMenu: TextView = v.findViewById(R.id.textIdMenu)
        val imgFotoMenu: ImageView = v.findViewById(R.id.imageViewMenu)
        val textNamaMenu: TextView = v.findViewById(R.id.textViewNamaMenu)
        val textHargaMenu: TextView = v.findViewById(R.id.textViewHargaMenu)

        val context = v.context;
        val cardMenu: CardView = v.findViewById(R.id.card_display_menu)


        fun bind(response: FoodResponse){
            val id:String = "${response.food_id}"
            val name = "${response.food_name}"
            val price = "${response.price}"
            val picture = "${response.food_picture}"

            textIdMenu.text = id
            textNamaMenu.text = name
            textHargaMenu.text = price
            var url = "http://192.168.100.87/rest_apiXXX/gambar/" + picture
            Picasso.get().load(url).into(imgFotoMenu)

            cardMenu.setOnClickListener {
                var cek = 0
                var result = false
                AdapterTransaction.amount = AdapterTransaction.listId.count()
                if(AdapterTransaction.listId.count() == 0){
                    AdapterTransaction.listId += id
                    AdapterTransaction.listName += name
                    AdapterTransaction.listPrice += price.toString().toInt()
                    AdapterTransaction.listPicture += picture
                    AdapterTransaction.listQty += 1
                    AdapterTransaction.price = AdapterTransaction.price + price.toString().toInt()
                }else{
                    while (cek < AdapterTransaction.listId.count()){
                        if (id == AdapterTransaction.listId[cek]){
                            result = false
                            break
                        }else{
                            result = true
                        }
                        cek++
                    }

                    if(result == true){
                        Toast.makeText(context,  "Id belum ada", Toast.LENGTH_SHORT).show()
                        AdapterTransaction.listId += id
                        AdapterTransaction.listName += name
                        AdapterTransaction.listPrice += price.toString().toInt()
                        AdapterTransaction.listPicture += picture
                        AdapterTransaction.listQty += 1
                        AdapterTransaction.price = AdapterTransaction.price + price.toString().toInt()
                    }else{
                        Toast.makeText(context,  "Id sudah ada", Toast.LENGTH_SHORT).show()
                        AdapterTransaction.listQty[cek] += 1
                    }
                }
            }
                }
        }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.card_layout_menu,
            parent,
             false)

        return ViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMenu[position])
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }
}