package com.atul.khatabook

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.atul.khatabook.fragments.HomeFragment
import java.text.SimpleDateFormat
import java.util.*

class AddTransaction : AppCompatActivity() {

    var valuess: String = ""
    public val homeFragment = HomeFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setTitle("Add Transaction")
        setContentView(R.layout.activity_add_transaction)

        val rdgrp = findViewById<RadioGroup>(R.id.rbrngrp)
        val rd1 = findViewById<RadioButton>(R.id.radioButton1)
        val rd2 = findViewById<RadioButton>(R.id.radioButton2)
        val datee = findViewById<EditText>(R.id.datee)
        datee.setText(SimpleDateFormat("dd-MM-yyyy").format(System.currentTimeMillis()))

        val myCalender = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalender.set(Calendar.YEAR, year)
            myCalender.set(Calendar.MONTH, month)
            myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd-MM-yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            datee.setText(sdf.format(myCalender.time))
        }
        datee.setOnClickListener {
            DatePickerDialog(this@AddTransaction, datePicker,
                myCalender.get(Calendar.YEAR),
                myCalender.get(Calendar.MONTH),
                myCalender.get(Calendar.DAY_OF_MONTH)).show()
        }





        rdgrp.setOnCheckedChangeListener { radioGroup: RadioGroup, i: Int ->
            if (rd2.isChecked) {
                valuess = "YOU GAVE"
            } else if (rd1.isChecked) {
                valuess = "YOU GOT"
            }
        }



        val addbtn = findViewById<Button>(R.id.addbtn)
        addbtn.setOnClickListener {

            if (!rd2.isChecked && !rd1.isChecked) {
                Toast.makeText(this@AddTransaction, "Please Fill Thse Data", Toast.LENGTH_LONG)
                    .show()
            } else {
                val db = SQLite(this, null,null,1)
                valuess.toString()
                datee.text.toString()

                val Name = findViewById<EditText>(R.id.customernm)
                val Mobileno = findViewById<EditText>(R.id.customermobile)
                val Amount = findViewById<EditText>(R.id.amount)
                val dat = datee.text.toString()

                val datew = dat
                val name = Name.text.toString()
                val mbo = Mobileno.text.toString()
                val amountt = Amount.text.toString()

                db.insertData(datew,name,mbo,valuess,amountt)
                Toast.makeText(this,"Transaction Added",Toast.LENGTH_LONG).show()

                datee.setText("")
                Name.setText("")
                Mobileno.setText("")
                Amount.setText("")
                rd1.isChecked = false
                rd2.isChecked = false

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }

        }
    }

}