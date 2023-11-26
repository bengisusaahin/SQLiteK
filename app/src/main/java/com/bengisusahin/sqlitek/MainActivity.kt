package com.bengisusahin.sqlitek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val myDatabase = this.openOrCreateDatabase("Musicians", MODE_PRIVATE,null)
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians (name VARCHAR, age INT)")
            //try catch kullandigimiz icin komut hatası yapsak app cokmez
            myDatabase.execSQL("INSERT INTO musicians (name,age) VALUES('James',50)")

            val cursor = myDatabase.rawQuery("SELECT * FROM musicians", null)

            val nameIx = cursor.getColumnIndex("name")
            val ageIx = cursor.getColumnIndex("age")

            while (cursor.moveToNext()){
                println("Name: " + cursor.getString(nameIx))
                println("Age: " + cursor.getInt(ageIx))
            }
            cursor.close()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }
}