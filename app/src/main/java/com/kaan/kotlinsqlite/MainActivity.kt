package com.kaan.kotlinsqlite

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        try {
            //değerleri dene hata alırsa
            //veritabanı nasıl oluşturulur. sonra sql gibi veri yazıp değerler oluştur.
            val myDatabase = this.openOrCreateDatabase("Musicians", Context.MODE_PRIVATE, null)
            //veritabanı üstünde sql çalıştırmak için
            //execSQL"" içerisine yazılacak kod sql kodu olacağı için hata almayabiliriz, bu sebeple try catch içinde yazılır.
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY, name VARCHAR, age INT)")

            //değer koymak
            //myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('Madonna', 62)")
            //myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('Billie', 19)")
            //myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('Taylor', 31)")

            //Veri Güncelleme
            //myDatabase.execSQL("UPDATE musicians SET age = 32 WHERE name = 'Taylor'")
            //myDatabase.execSQL("UPDATE musicians SET name = 'Taylor Swift' WHERE id = 3")

            //Silme işlemi
            myDatabase.execSQL("DELETE FROM musicians WHERE name = 'Billie'")

            //değer çekmek
            //val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE name LIKE 'M%'", null)
            val cursor = myDatabase.rawQuery("SELECT * FROM musicians", null)

            //name ve age'in indexlerini alıp Ix değerlere eşitledik
            val nameIx = cursor.getColumnIndex("name")
            val ageIx = cursor.getColumnIndex("age")
            val idIx = cursor.getColumnIndex("id")

            //moveToNext hücreler içerisinde gezsin hepsinde
            while (cursor.moveToNext()) {
                //hücrelere tek tek tıklayıp seçilenleri yazdıracak
                println("Name: " + cursor.getString(nameIx))
                println("Age: " + cursor.getInt(ageIx))
                println("Id: " + cursor.getInt(idIx))

            }
            cursor.close()


        }   catch (e: Exception) {
            //detaylarını incele
            e.printStackTrace()
        }


    }
}