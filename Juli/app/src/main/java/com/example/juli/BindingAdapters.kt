package com.example.juli

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.juli.network.Foto
import com.example.juli.overview.ViewPager2Adapter


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?){
    imgUrl?.let{
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }

}

class BindingAdapters {
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: ViewPager2, data: List<Foto>?){
    val adapter = recyclerView.adapter as ViewPager2Adapter
    adapter.submitList(data)
}