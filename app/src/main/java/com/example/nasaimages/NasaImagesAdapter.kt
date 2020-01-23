package com.example.nasaimages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nasaimages.model.NasaImages
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class NasaImagesAdapter(
    private var listOfNasaImages: NasaImages? // need to get list of NasaImages
) : RecyclerView.Adapter<NasaImagesAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(position, listOfNasaImages)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.nasa_images_cell, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 1 // use listOfNasaImages.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(
            position: Int, // based on position we will get respective NasaImages object
            nasaImagesList: NasaImages?
        ) {
            itemView.apply {
                nasaImagesList?.let {
                    val builder = Picasso.Builder(context)
                    builder.downloader(OkHttp3Downloader(context))
                    builder.build().load(it.url)
                        .into(findViewById<ImageView>(R.id.nasa_image))
                    findViewById<AppCompatTextView>(R.id.nasa_image_copyright).text = it.copyright
                    findViewById<AppCompatTextView>(R.id.nasa_image_date).text = it.date
                }
            }
        }
    }
}