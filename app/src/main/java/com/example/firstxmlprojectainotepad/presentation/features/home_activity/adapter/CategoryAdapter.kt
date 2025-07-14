package com.example.firstxmlprojectainotepad.presentation.features.home_activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.firstxmlprojectainotepad.R
import com.example.firstxmlprojectainotepad.databinding.CategoryItemLayoutBinding

class CategoryAdapter(
    val categoriesItem: List<Int>
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private var selectedPosition = 0
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val binding =
            CategoryItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {
        val category = categoriesItem[position]
        holder.bind(item = category, position == selectedPosition)
        holder.binding.BackgroundCategoryText.setOnClickListener {
            val previous = selectedPosition
            selectedPosition = position
            notifyItemChanged(previous)
            notifyItemChanged(selectedPosition)
        }
    }

    override fun getItemCount(): Int {
        return categoriesItem.size
    }

    inner class CategoryViewHolder(val binding: CategoryItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Int, isSelected: Boolean) {
            binding.CategoryText.text = binding.root.context.getString(item)
            binding.BackgroundCategoryText.isSelected = isSelected
            if (isSelected) {
                val color = ContextCompat.getColor(binding.root.context, R.color.white)
                val bgColor = ContextCompat.getDrawable(
                    binding.root.context,
                    R.drawable.category_item_border_bg
                )
                binding.CategoryText.setTextColor(color)
                binding.BackgroundCategoryText.background = bgColor

            } else {
                val bgColor = ContextCompat.getDrawable(
                    binding.root.context,
                    R.drawable.category_item_border_bg
                )
                val color = ContextCompat.getColor(binding.root.context, R.color.grayD3)
                binding.CategoryText.setTextColor(color)
                binding.BackgroundCategoryText.background = bgColor
            }

        }
    }
}