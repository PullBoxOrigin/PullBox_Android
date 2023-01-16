package com.antique_boss.portfolio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.antique_boss.portfolio.R
import com.antique_boss.portfolio.databinding.ListItemPortfolioBinding
import com.bumptech.glide.Glide

class PortfolioListAdapter(
    private val onPortfolioClickListener: () -> Unit
) : RecyclerView.Adapter<PortfolioListAdapter.PortfolioViewHolder>() {
    private val portfolioList = mutableListOf<String>()

    inner class PortfolioViewHolder(private val binding: ListItemPortfolioBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            Glide.with(binding.profileImageView.context)
                .load("https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2022/10/13/4d8ef85c-ed4f-4f08-843e-19f406616942.jpg")
                .into(binding.profileImageView)

            binding.categoryTextView.text = "#Android"
            binding.nicknameTextView.text = "엔틱보스"
            binding.portfolioQuestionTextView.text = portfolioList[position]
            binding.root.setOnClickListener {
                onPortfolioClickListener()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortfolioViewHolder {
        val binding = DataBindingUtil.inflate<ListItemPortfolioBinding>(LayoutInflater.from(parent.context),
            R.layout.list_item_portfolio, parent, false)
        return PortfolioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PortfolioViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = portfolioList.size

    fun submitList(newList: List<String>) {
        portfolioList.addAll(newList)
        notifyDataSetChanged()
    }
}