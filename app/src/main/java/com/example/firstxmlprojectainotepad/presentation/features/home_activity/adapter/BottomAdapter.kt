package com.example.firstxmlprojectainotepad.presentation.features.home_activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.firstxmlprojectainotepad.R
import com.example.firstxmlprojectainotepad.databinding.BottomSheetRecyclerViewItemBinding
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.BottomSheetItem
import com.example.firstxmlprojectainotepad.presentation.features.home_activity.bottomSheetItems

class BottomAdapter(
    val bottomSheetItem: List<BottomSheetItem>,
    var onItemSelected:(position:Int)-> Unit,
) : RecyclerView.Adapter<BottomAdapter.BottomViewHolder>() {
    private var selectedPosition = 0
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BottomViewHolder {
        val binding = BottomSheetRecyclerViewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BottomViewHolder(binding = binding)
    }

    override fun onBindViewHolder(
        holder: BottomViewHolder,
        position: Int
    ) {
        val bottomSheetViewHolder = bottomSheetItems[position]
        holder.bind(bottomSheetViewHolder = bottomSheetViewHolder, position ==selectedPosition )
        holder.binding.BottomSheetItem.setOnClickListener {
            val previous=selectedPosition
            selectedPosition=position
            notifyItemChanged(previous)
            notifyItemChanged(selectedPosition)

            onItemSelected(position)
        }
    }

    override fun getItemCount(): Int {
        return bottomSheetItem.size
    }

    inner class BottomViewHolder(val binding: BottomSheetRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bottomSheetViewHolder: BottomSheetItem, isSelected: Boolean) {
            binding.BottomSheetImage.setImageResource(bottomSheetViewHolder.icon)
            binding.BottomSheetText.setText(bottomSheetViewHolder.title)

            if(isSelected){
                val selectedColor= ContextCompat.getColor(binding.root.context, R.color.purpleF0)
                binding.BottomSheetText.setTextColor(selectedColor)
                binding.BottomSheetImage.setColorFilter(selectedColor)
            }else{
                val nonSelectedColor= ContextCompat.getColor(binding.root.context, R.color.grayB1)
                binding.BottomSheetText.setTextColor(nonSelectedColor)
                binding.BottomSheetImage.setColorFilter(nonSelectedColor)
            }
        }
    }

}