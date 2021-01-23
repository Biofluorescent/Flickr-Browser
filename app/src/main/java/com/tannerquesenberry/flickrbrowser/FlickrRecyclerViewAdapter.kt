package com.tannerquesenberry.flickrbrowser

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

// Place view holders outside of the adapter class
class FlickrImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var thumbnail: ImageView = view.findViewById(R.id.thumbnail)
    var title: TextView = view.findViewById(R.id.imageTitle)
}

class FlickrRecyclerViewAdapter(private var photoList: List<Photo>): RecyclerView.Adapter<FlickrImageViewHolder>() {
    // Log tags can be a max of 23 characters
    private val TAG = "FlickrRecyclerViewAdapt"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrImageViewHolder {
        // Called by the layout manager when it needs a new view
        Log.d(TAG, ".onCreateViewHolder new view requested")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.browse, parent, false)
        return FlickrImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlickrImageViewHolder, position: Int) {
        // Called by the layout manager when it wants new data in an existing view
        if (photoList.isEmpty()) {
            holder.thumbnail.setImageResource(R.drawable.placeholder)
            //Get the string resource
            holder.title.setText(R.string.empty_photo)
        }else {
            val photoItem = photoList[position]
            Log.d(TAG, ".onBindViewHolder: ${photoItem.title} --> $position")

            // Picasso downloads the image in a background thread on its own
            Picasso.get().load(photoItem.image)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail)

            holder.title.text = photoItem.title
        }
    }

    override fun getItemCount(): Int {
//        Log.d(TAG, ".getItemCount called")
        //1 is for the placeholder image if no search results
        return if (photoList.isNotEmpty()) photoList.size else 1
    }

    fun loadNewData(newPhotos: List<Photo>) {
        photoList = newPhotos
        notifyDataSetChanged()
    }

    fun getPhoto(position: Int): Photo? {
        return if (photoList.isNotEmpty()) photoList[position] else null
    }
}