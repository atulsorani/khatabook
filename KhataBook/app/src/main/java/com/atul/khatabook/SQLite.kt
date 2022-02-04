package com.atul.khatabook

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.atul.khatabook.fragments.HomeFragment

class SQLite(
    context: Context,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) :
    SQLiteOpenHelper(context, "DBASE", factory, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE CUSTOMER(ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,NAME TEXT,MOBILENO TEXT,TRANCE TEXT,AMOUNT LONG)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS CUSTOMER")
    }

    fun insertData(Date: String, Name: String, Mobileno: String, Trance: String, Amount: String) {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put("DATE", Date)
        values.put("NAME", Name)
        values.put("MOBILENO", Mobileno)
        values.put("TRANCE", Trance)
        values.put("AMOUNT", Amount)

        db.insert("CUSTOMER", null, values)
        db.close()
    }

    fun getCustomer(mCtx: HomeFragment): ArrayList<Customer> {
        val qry = "Select * from CUSTOMER order by ID desc"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)
        val customers = ArrayList<Customer>()

        if (cursor.count == 0) {
            Toast.makeText(mCtx.context, "No record Found", Toast.LENGTH_LONG)
        } else {
            while (cursor.moveToNext()) {
                val customer = Customer()
                customer.name = cursor.getString(2)
                customer.date = cursor.getString(1)
                customer.amount = cursor.getString(5)
                customers.add(customer)
            }
        }
        cursor.close()
        db.close()
        return customers
    }

    fun getrecivedCustomer(mCtx: HomeFragment): ArrayList<Customer> {
        val qry = "Select * from CUSTOMER where TRANCE = 'YOU GOT' order by ID desc"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)
        val customers = ArrayList<Customer>()

        if (cursor.count == 0) {
            Toast.makeText(mCtx.context, "No record Found", Toast.LENGTH_LONG)
        } else {
            while (cursor.moveToNext()) {
                val customer = Customer()
                customer.name = cursor.getString(2)
                customer.date = cursor.getString(1)
                customer.amount = cursor.getString(5)
                customers.add(customer)
            }
        }
        cursor.close()
        db.close()
        return customers
    }

    fun getdueCustomer(mCtx: HomeFragment): ArrayList<Customer> {
        val qry = "Select * from CUSTOMER where TRANCE = 'YOU GAVE' order by ID desc"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)
        val customers = ArrayList<Customer>()

        if (cursor.count == 0) {
            Toast.makeText(mCtx.context, "No record Found", Toast.LENGTH_LONG)
        } else {
            var tot: Long
            while (cursor.moveToNext()) {
                val customer = Customer()
                customer.name = cursor.getString(2)
                customer.date = cursor.getString(1)
                customer.amount = cursor.getLong(5).toString()
                tot = cursor.getLong(5)
                tot++
                customers.add(customer)
            }
        }
        cursor.close()
        db.close()
        return customers
    }

    fun totgetdueCustomer(mCtx: HomeFragment): Cursor? {
        val qry = "SELECT SUM(AMOUNT) as Total FROM  CUSTOMER where TRANCE = 'YOU GAVE' order by ID desc"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)
        return cursor

    }

    fun totgetreciveCustomer(mCtx: HomeFragment): Cursor? {
        val qry = "SELECT SUM(AMOUNT) as Total FROM  CUSTOMER where TRANCE = 'YOU GOT' order by ID desc"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)
        return cursor

    }

}