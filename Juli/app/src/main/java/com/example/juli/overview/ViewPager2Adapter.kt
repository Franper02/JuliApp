package com.example.juli.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.juli.R
import com.example.juli.databinding.FotoViewItemBinding
import com.example.juli.network.Foto

class ViewPager2Adapter :  ListAdapter<Foto,
        ViewPager2Adapter.MarsPhotosViewHolder>(DiffCallback) {


    class MarsPhotosViewHolder(
        private var binding: FotoViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(marsPhoto: Foto) {
            binding.foto = marsPhoto

            binding.card.setOnClickListener {


                val animation = AnimationUtils.loadAnimation(binding.card.context, R.anim.fade_out)
                val animationIn = AnimationUtils.loadAnimation(binding.card.context, R.anim.fade_in)

                when(binding.fotosView.visibility){
                    View.VISIBLE -> {
                        binding.fotosView.startAnimation(animation)
                        animation.setAnimationListener(object : Animation.AnimationListener{
                            override fun onAnimationStart(p0: Animation?){}

                            override fun onAnimationEnd(p0: Animation?) {
                                binding.fotosView.visibility = View.GONE
                                binding.mensaje.startAnimation(animationIn)
                                animationIn.setAnimationListener(object : Animation.AnimationListener{
                                    override fun onAnimationStart(p0: Animation?){
                                        binding.mensaje.visibility = View.VISIBLE
                                    }

                                    override fun onAnimationEnd(p0: Animation?){}

                                    override fun onAnimationRepeat(p0: Animation?){}
                                })
                                binding.mensaje.visibility = View.VISIBLE
                            }

                            override fun onAnimationRepeat(p0: Animation?){}
                        })

                    }
                    else -> {
                        binding.mensaje.startAnimation(animation)
                        animation.setAnimationListener(object : Animation.AnimationListener{
                            override fun onAnimationStart(p0: Animation?){}

                            override fun onAnimationEnd(p0: Animation?) {
                                binding.mensaje.visibility = View.GONE
                                binding.fotosView.startAnimation(animationIn)
                                animationIn.setAnimationListener(object : Animation.AnimationListener{
                                    override fun onAnimationStart(p0: Animation?){
                                        binding.fotosView.visibility = View.VISIBLE
                                    }

                                    override fun onAnimationEnd(p0: Animation?){}

                                    override fun onAnimationRepeat(p0: Animation?){}
                                })
                                binding.fotosView.visibility = View.VISIBLE
                            }

                            override fun onAnimationRepeat(p0: Animation?){}
                        })
                    }

                }

            }
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<Foto>(){
        override fun areItemsTheSame(oldItem: Foto, newItem: Foto): Boolean {
            return oldItem.id ==  newItem.id
        }

        override fun areContentsTheSame(oldItem: Foto, newItem: Foto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPager2Adapter.MarsPhotosViewHolder {

        return MarsPhotosViewHolder(FotoViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    override fun onBindViewHolder(holder: ViewPager2Adapter.MarsPhotosViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }




}