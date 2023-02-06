package com.demir.kriptoborsa.adepter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demir.kriptoborsa.R
import com.demir.kriptoborsa.model.KriptoModel


class Adepter(private val kriptoList:ArrayList<KriptoModel>,private val listerner:Listener): RecyclerView.Adapter<Adepter.KriptoHolder>() {
    interface Listener{
        fun onClicked(kriptoModel: KriptoModel)
    }
    var colors :Array<String> = arrayOf( "#540375","#10A19D","#FFBF00","#D989B5","#68B984")

    class KriptoHolder(view: View):RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KriptoHolder {
       val gorunum= LayoutInflater.from(parent.context).inflate(R.layout.row,parent,false)
        return KriptoHolder(gorunum)
    }

    override fun onBindViewHolder(holder: KriptoHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.textName).text =
            kriptoList.get(position).currency
        holder.itemView.findViewById<TextView>(R.id.textPrice).text = kriptoList.get(position).price
        holder.itemView.setBackgroundColor(Color.parseColor(colors.get(position % 6)))

        holder.itemView.setOnClickListener {
            listerner.onClicked(kriptoList[position])

        }
    }

    override fun getItemCount(): Int {
       return kriptoList.size
    }
}