package com.antique_boss.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.antique_boss.news.databinding.ListItemHotPostBinding
import com.antique_boss.util.preview.Preview
import com.bumptech.glide.Glide

class HotPostAdapter : RecyclerView.Adapter<HotPostAdapter.HotPostViewHolder>() {
    private val previews = mutableListOf<Preview>()

    inner class HotPostViewHolder(private val binding: ListItemHotPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.preview = previews[position]

            Glide.with(binding.previewImageView.context)
                .load(previews[position].imageUrl)
                .into(binding.previewImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotPostViewHolder {
        val binding = DataBindingUtil.inflate<ListItemHotPostBinding>(LayoutInflater.from(parent.context), R.layout.list_item_hot_post, parent, false)
        return HotPostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotPostViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = previews.size

    fun submitList(previews: List<Preview>) {
        this.previews.addAll(previews)
        notifyDataSetChanged()
    }
}