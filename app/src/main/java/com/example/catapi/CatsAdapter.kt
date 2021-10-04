package com.example.catapi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load

class CatsAdapter(var mContext: Context) : RecyclerView.Adapter<CatsAdapter.CatViewHolder>() {

    private val items = mutableListOf<Cat>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder, parent,false)
        return CatViewHolder(view)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val imageUrl = items[position].url
        holder.bind(imageUrl)

        holder.imageView.setOnClickListener{
            val intent = Intent(mContext,FragmentActivity::class.java)
            intent.putExtra("image_url",items[position].url)
            mContext.startActivity(intent)
        }

    }

    fun addItems(newItems: List<Cat>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }


  inner class CatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val imageView = view.findViewById<ImageView>(R.id.image_post)

    fun bind(imageUrl: String) {
        imageView.load(imageUrl)
    }

  }
}
