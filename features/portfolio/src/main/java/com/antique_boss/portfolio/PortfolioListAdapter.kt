package com.antique_boss.portfolio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.antique_boss.portfolio.databinding.ListItemPortfolioBinding

class PortfolioListAdapter : RecyclerView.Adapter<PortfolioListAdapter.PortfolioViewHolder>() {
    private val portfolioList = mutableListOf<String>()

    inner class PortfolioViewHolder(private val binding: ListItemPortfolioBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.categoryTextView.text = "#Android"
            binding.nicknameTextView.text = "엔틱보스"
            binding.portfolioQuestionTextView.text = portfolioList[position]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortfolioViewHolder {
        val binding = DataBindingUtil.inflate<ListItemPortfolioBinding>(LayoutInflater.from(parent.context), R.layout.list_item_portfolio, parent, false)
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