package com.example.catapi

import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.api.load


class FragmentActivity: AppCompatActivity() {


    private lateinit var  button: Button
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_activity)
        val imageUrl = intent.getStringExtra("image_url")

        button = findViewById(R.id.button)
        image = findViewById(R.id.imageCat)

        image.load(imageUrl)

        button.setOnClickListener {
            Toast.makeText(this,"${saveImage("Cat")}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveImage( title:String):Uri{
        val drawable = image.drawable
        val bitmap = (drawable as BitmapDrawable).bitmap
        val savedImageURL = MediaStore.Images.Media.insertImage(
            contentResolver,
            bitmap,
            title,
            "Image of $title"
        )

        return Uri.parse(savedImageURL)
    }
}
