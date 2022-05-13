package com.atahar.flickrimage.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.atahar.domain.entities.FlkrPhoto
import com.atahar.flickrimage.databinding.PhotoSingleItemBinding

class PhotoAdapter :
    ListAdapter<FlkrPhoto, PhotoAdapter.PhotoViewHolder>(DiffCallback) {

    class PhotoViewHolder(
        private var binding: PhotoSingleItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: FlkrPhoto) {
            binding.photo = photo
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<FlkrPhoto>() {
        override fun areItemsTheSame(oldItem: FlkrPhoto, newItem: FlkrPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FlkrPhoto, newItem: FlkrPhoto): Boolean {
            return oldItem.secret == newItem.secret
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoViewHolder {
        return PhotoViewHolder(
            PhotoSingleItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bind(photo)
    }
}
