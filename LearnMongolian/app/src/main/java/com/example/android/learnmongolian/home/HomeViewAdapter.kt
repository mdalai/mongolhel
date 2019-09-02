package com.example.android.learnmongolian.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.learnmongolian.databinding.HomeViewItemBinding
import com.example.android.learnmongolian.network.GroupProperty

class HomeViewAdapter(private val onClickListener: OnClickListner) :
    ListAdapter<GroupProperty, HomeViewAdapter.GroupPropertyViewHolder>(DiffCallback) {

    class GroupPropertyViewHolder(private var binding: HomeViewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(groupProperty: GroupProperty) {
            binding.property = groupProperty
            binding.executePendingBindings()
        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<GroupProperty>() {
        override fun areItemsTheSame(oldItem: GroupProperty, newItem: GroupProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GroupProperty, newItem: GroupProperty): Boolean {
            return oldItem.group_id == newItem.group_id
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupPropertyViewHolder {
        return GroupPropertyViewHolder(HomeViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: GroupPropertyViewHolder, position: Int) {
        val groupProperty = getItem(position)
        holder.itemView.setOnClickListener { onClickListener.onClick(groupProperty) }
        holder.bind(groupProperty)

    }

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [GroupProperty]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [GroupProperty]
     */
    class OnClickListner(private val clickListener: (groupProperty: GroupProperty) -> Unit){
        fun onClick(groupProperty: GroupProperty) = clickListener(groupProperty)
    }

}