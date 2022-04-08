package com.example.mymovieapp.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.mymovieapp.utils.Constants

object ImageBindingAdapter {
  @BindingAdapter("imageUrl")
  @JvmStatic
  fun loadImage(imageView: ImageView, url: String) {
    if(url.isNotEmpty()) {
      Glide.with(imageView.context)
        .load(Constants.IMAGE_BASE_URL + url)
        .into(imageView)
    }
  }
}

