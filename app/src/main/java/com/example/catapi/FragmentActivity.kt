package com.example.catapi

import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.api.load


class FragmentActivity: AppCompatActivity() {


    private lateinit var  button: Button
    private lateinit var image: ImageView
    private lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_activity)
        val imageUrl = intent.getStringExtra("image_url")

        button = findViewById(R.id.button)
        image = findViewById(R.id.imageCat)
        text = findViewById(R.id.textView)

        image.load(imageUrl)

        button.setOnClickListener {
            text.text = "Saved: ${saveImage("cat")}"
        }
    }

    private fun saveImage( title:String):Uri{
        // Get the image from drawable resource as drawable object
        val drawable = image.drawable

        // Get the bitmap from drawable object
        val bitmap = (drawable as BitmapDrawable).bitmap

        // Save image to gallery
        val savedImageURL = MediaStore.Images.Media.insertImage(
            contentResolver,
            bitmap,
            title,
            "Image of $title"
        )

        // Parse the gallery image url to uri
        return Uri.parse(savedImageURL)
    }
}