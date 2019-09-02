package com.example.android.learnmongolian

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.learnmongolian.home.HomeViewAdapter
import com.example.android.learnmongolian.home.WebApiStatus
import com.example.android.learnmongolian.network.GroupProperty


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("http").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

// binding adapter for listData
@BindingAdapter("list_Data")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<GroupProperty>?){
    val adapter =  recyclerView.adapter as HomeViewAdapter
    adapter.submitList(data)
}

// show Status in ImageView per the status value
@BindingAdapter("webApiStatus")
fun bindStatus(statusImageView: ImageView, status: WebApiStatus?){
    when (status) {
        WebApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        WebApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        WebApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}