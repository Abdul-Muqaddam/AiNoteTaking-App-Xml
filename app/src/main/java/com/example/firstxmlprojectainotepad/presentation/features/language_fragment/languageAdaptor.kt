package com.example.firstxmlprojectainotepad.presentation.features.language_fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstxmlprojectainotepad.databinding.LanguageItemLayoutBinding

class LanguageAdaptor(
    private val language: List<LanguageModel>
) : RecyclerView.Adapter<LanguageAdaptor.LanguageViewHolder>() {

    private var selectedPosition = 0

    inner class LanguageViewHolder(val binding: LanguageItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(language: LanguageModel, isSelected: Boolean) {
            binding.languageText.text = language.displayName
            binding.localLanguageName.text = "(${language.localName})"
            binding.languageItem.isSelected = isSelected
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LanguageViewHolder {
        val binding =
            LanguageItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LanguageViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: LanguageViewHolder,
        position: Int
    ) {

        val language = language[position]
        holder.bind(language = language, position == selectedPosition)

        holder.binding.languageItem.setOnClickListener {
            val previous = selectedPosition
            selectedPosition = position
            notifyItemChanged(previous)
            notifyItemChanged(selectedPosition)
        }
    }

    override fun getItemCount(): Int {
        return language.size
    }
}