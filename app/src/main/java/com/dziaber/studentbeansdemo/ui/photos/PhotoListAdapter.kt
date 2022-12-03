package com.dziaber.studentbeansdemo.ui.photos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dziaber.studentbeansdemo.R
import com.squareup.picasso.Picasso

/**
 * A RecyclerView Adapter class used for displaying *Photo* objects
 */
class PhotoListAdapter(private val dataSet: List<Photo>) :
    RecyclerView.Adapter<PhotoListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView
        val photoView: ImageView

        init {
            titleView = view.findViewById(R.id.title_view)
            photoView = view.findViewById(R.id.photo_view)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.photo_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.titleView.text = dataSet[position].title
        val url = dataSet[position].thumbnailUrl
        Picasso.get().load(url).into(viewHolder.photoView)
    }

    override fun getItemCount() = dataSet.size


}