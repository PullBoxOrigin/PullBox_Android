package com.antique_boss.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.antique_boss.news.databinding.ListItemHotPostBinding
import com.bumptech.glide.Glide

class HotPostAdapter(
    private val hotPosts: List<String>
) : RecyclerView.Adapter<HotPostAdapter.HotPostViewHolder>() {

    inner class HotPostViewHolder(private val binding: ListItemHotPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            Glide.with(binding.previewImageView.context)
                .load("https://www.dailypop.kr/news/photo/201606/19230_18781_5343.jpg")
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

    override fun getItemCount(): Int = hotPosts.size
}