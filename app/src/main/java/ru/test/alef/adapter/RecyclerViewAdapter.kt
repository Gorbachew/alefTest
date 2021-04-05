package ru.lifehack.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.test.alef.MainActivity
import ru.test.alef.R
import ru.test.alef.api.PicassoInstance

class RecyclerViewAdapter(private val mainActivity: MainActivity, private val images: List<String>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount() = images.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.grid_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        PicassoInstance(images[position], holder.imageView!!).load()
        holder.imageView?.setOnClickListener {
            mainActivity.viewModel.setImageUrl(images[position])
            mainActivity.navController.navigate(R.id.action_imagesFragment_to_imageFragment)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imageView: ImageView? = null

        init {
            imageView = itemView.findViewById(R.id.imageView)

        }
    }
}