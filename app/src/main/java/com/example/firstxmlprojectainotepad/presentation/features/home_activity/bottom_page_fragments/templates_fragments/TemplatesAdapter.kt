package com.example.firstxmlprojectainotepad.presentation.features.home_activity.bottom_page_fragments.templates_fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstxmlprojectainotepad.R


class TemplatesAdapter(
    private val colors: List<Int>,
    private val onTemplateClicks: (Int) -> Unit
) : RecyclerView.Adapter<TemplatesAdapter.TemplatesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TemplatesViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.templates_item_card, parent, false)

        return TemplatesViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TemplatesViewHolder,
        position: Int
    ) {

        val color = colors[position]
        holder.itemView.setBackgroundColor(colors[position])
        holder.itemView.setOnClickListener() {
            onTemplateClicks(color)
        }
    }

    override fun getItemCount(): Int=colors.size


    inner class TemplatesViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)
}