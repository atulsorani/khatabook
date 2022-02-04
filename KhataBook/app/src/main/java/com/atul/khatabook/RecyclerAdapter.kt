package com.atul.khatabook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.atul.khatabook.fragments.HomeFragment

class RecyclerAdapter(mCtx: HomeFragment, val customers: ArrayList<Customer> ) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    val mCtx = mCtx

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setonItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.lyout_rv, parent, false)
        return ViewHolder(v,mListener)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {

        val customer : Customer = customers[position]

        //holder.itemImage.setImageResource(img[position])
        holder.itemName.text = customer.name
        holder.itemDate.text = customer.date
        holder.itemAmnount.text = customer.amount
    }

    override fun getItemCount(): Int {
        return customers.size
    }

    class ViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var itemName: TextView
        var itemDate: TextView
        var itemAmnount: TextView

        init {
            itemImage = itemView.findViewById(R.id.prodp)
            itemName = itemView.findViewById(R.id.fname)
            itemDate = itemView.findViewById(R.id.Tdate)
            itemAmnount = itemView.findViewById(R.id.amounts)

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}