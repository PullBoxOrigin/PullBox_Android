package com.antique_boss.registration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.antique_boss.registration.databinding.ListItemCategoryBinding

class CategoryAdapter(
    private val categories: List<String>,
    private val onCategorySelectedListener: (String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(private val binding: ListItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.category = categories[position]
            binding.categoryTextView.setOnClickListener {
                onCategorySelectedListener(categories[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = DataBindingUtil.inflate<ListItemCategoryBinding>(LayoutInflater.from(parent.context), R.layout.list_item_category, parent, false)

        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = categories.size

}