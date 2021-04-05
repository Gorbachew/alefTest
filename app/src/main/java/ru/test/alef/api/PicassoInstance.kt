package ru.test.alef.api

import android.widget.ImageView
import com.squareup.picasso.Picasso
import ru.test.alef.R

class PicassoInstance(val url: String, val view: ImageView) {

    fun load() {
        Picasso.get()
            .load(url)
            .error(R.drawable.not_loaded)
            .into(view)
    }
}