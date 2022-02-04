package com.atul.khatabook.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.atul.khatabook.AddTransaction
import com.atul.khatabook.Customer
import com.atul.khatabook.RecyclerAdapter
import com.atul.khatabook.SQLite
import com.atul.khatabook.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    var tot: Int? = null
    var tott: Int? = null
    var tooop: String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bind = FragmentHomeBinding.inflate(layoutInflater)

        //total of you will get
        val db = this.context?.let { SQLite(it, null,null,1) }
        val data: Cursor? = db!!.totgetdueCustomer(this)
        while (data!!.moveToNext()) {
           tot = data.getInt(0)
            tooop = tot.toString()
            bind.uwget.setText(tooop)
        }

        //total of you will give

        val dataa: Cursor? = db!!.totgetreciveCustomer(this)
        while (dataa!!.moveToNext()) {
            tott = dataa.getInt(0)
            var toop = tott.toString()
            bind.uwgive.setText(toop)
        }

        //total on hand
        var tohand = tott?.minus(tot!!)
        var moneyh:String = tohand.toString()
        bind.monhand.setText(moneyh)


        //View all Customer
         fun viewAllCustomer(){

             layoutManager = LinearLayoutManager(this@HomeFragment.requireContext())
             val db = this.context?.let { SQLite(it, null,null,1) }
             val customerlist: ArrayList<Customer> = db!!.getCustomer(this)
             val adapter = RecyclerAdapter(this,customerlist)
             bind.recyclerview.layoutManager = layoutManager
             bind.recyclerview.adapter = adapter
            adapter.setonItemClickListener(object : RecyclerAdapter.onItemClickListener{
                override fun onItemClick(position: Int) {
                    Toast.makeText(getActivity(),"$position",Toast.LENGTH_SHORT).show();
                }
            })

        }
        viewAllCustomer()

        //view recived Customer
        fun viewrecivedCustomer(){

            layoutManager = LinearLayoutManager(this@HomeFragment.requireContext())
            val db = this.context?.let { SQLite(it, null,null,1) }
            val customerlist: ArrayList<Customer> = db!!.getrecivedCustomer(this)
            val adapter = RecyclerAdapter(this,customerlist)
            bind.reciedrecyclerview.layoutManager = layoutManager
            bind.reciedrecyclerview.adapter = adapter
        }
        viewrecivedCustomer()

        //view due Customer
        fun viewdueCustomer(){

            layoutManager = LinearLayoutManager(this@HomeFragment.requireContext())
            val db = this.context?.let { SQLite(it, null,null,1) }
            val customerlist: ArrayList<Customer> = db!!.getdueCustomer(this)
            val adapter = RecyclerAdapter(this,customerlist)
            bind.duerecyclerview.layoutManager = layoutManager
            bind.duerecyclerview.adapter = adapter
        }
        viewdueCustomer()



        bind.alltrabtn.setOnClickListener {
            bind.recyclerview.visibility = View.VISIBLE
            bind.reciedrecyclerview.visibility = View.INVISIBLE
            bind.duerecyclerview.visibility = View.INVISIBLE

            bind.recivetra.setTextColor(Color.parseColor("#d3dae3"))
            bind.alltrabtn.setTextColor(Color.parseColor("#FF018786"))
            bind.duetra.setTextColor(Color.parseColor("#d3dae3"))
        }

        bind.recivetra.setOnClickListener {
            bind.recyclerview.visibility = View.INVISIBLE
            bind.duerecyclerview.visibility = View.INVISIBLE
            bind.reciedrecyclerview.visibility = View.VISIBLE

            bind.recivetra.setTextColor(Color.parseColor("#FF018786"))
            bind.alltrabtn.setTextColor(Color.parseColor("#d3dae3"))
            bind.duetra.setTextColor(Color.parseColor("#d3dae3"))
        }

        bind.duetra.setOnClickListener {
            bind.recyclerview.visibility = View.INVISIBLE
            bind.duerecyclerview.visibility = View.VISIBLE
            bind.reciedrecyclerview.visibility = View.INVISIBLE

            bind.recivetra.setTextColor(Color.parseColor("#d3dae3"))
            bind.alltrabtn.setTextColor(Color.parseColor("#d3dae3"))
            bind.duetra.setTextColor(Color.parseColor("#FF018786"))
        }

        bind.btntras.setOnClickListener {
            val intent = Intent(this@HomeFragment.requireContext(), AddTransaction::class.java)
            startActivity(intent)
            requireActivity().onBackPressed()
        }

        return bind.root
    }


}