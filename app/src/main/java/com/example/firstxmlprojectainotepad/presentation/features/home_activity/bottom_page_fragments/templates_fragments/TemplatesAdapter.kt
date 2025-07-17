package com.example.firstxmlprojectainotepad.presentation.features.home_activity.bottom_page_fragments.templates_fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstxmlprojectainotepad.R

class TemplatesAdapter(private val colors: List<Int>): RecyclerView.Adapter<TemplatesAdapter.TemplatesViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TemplatesViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.templates_item_card,parent,false) as CardView

        return TemplatesViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TemplatesViewHolder,
        position: Int
    ) {
        holder.cardView.setCardBackgroundColor(colors[position])
    }

    override fun getItemCount(): Int=colors.size

    inner class TemplatesViewHolder(val cardView: CardView): RecyclerView.ViewHolder(cardView)
}