package com.example.firstxmlprojectainotepad.presentation.features.Chat_Page

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstxmlprojectainotepad.databinding.ItemAiResponseBinding
import com.example.firstxmlprojectainotepad.databinding.ItemHumanChatBinding


data class ChatMessage(
    val message: String,
    val isUser: Boolean,
    val isLoading: Boolean=false
)

class ChatAdapter(private val messages: List<ChatMessage>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_USER = 1
        private const val VIEW_TYPE_BOT = 2
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_USER) {

            val binding = ItemHumanChatBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            UserViewHolder(binding)
        } else {
            val binding = ItemAiResponseBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            BotViewHolder(binding)
        }
    }
    override fun getItemViewType(position: Int): Int {
        return if (messages[position].isUser) VIEW_TYPE_USER else VIEW_TYPE_BOT
    }


    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val message = messages[position]
        if (holder is UserViewHolder) {
            holder.binding.itemHumanQuery.text = message.message
        } else if (holder is BotViewHolder) {

            if (message.isLoading) {

                holder.binding.ChatingAnimation.visibility = View.VISIBLE
                holder.binding.ChatingAnimation.playAnimation()

                holder.binding.itemAiResponse.visibility = View.GONE
            } else {

                holder.binding.ChatingAnimation.cancelAnimation()
                holder.binding.ChatingAnimation.visibility = View.GONE

                holder.binding.itemAiResponse.text = message.message
                holder.binding.LayoutOfAiBot.visibility = View.VISIBLE
            }


        }
    }

    override fun getItemCount(): Int = messages.size

    class UserViewHolder(val binding: ItemHumanChatBinding) : RecyclerView.ViewHolder(binding.root){

    }

    class BotViewHolder(val binding: ItemAiResponseBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}