package com.leit.whattodoapp.ui

import android.content.ClipData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.leanback.widget.DiffCallback
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leit.whattodoapp.data.Activity
import com.leit.whattodoapp.databinding.ActivityItemBinding
import com.leit.whattodoapp.databinding.FragmentListActivityBinding

class ActivityListAdapter(private val onActivityClicked:(Activity) -> Unit):
    ListAdapter<Activity, ActivityListAdapter.ActivityViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        return ActivityViewHolder(
            ActivityItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )

        )
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onActivityClicked(current)
        }
        holder.bind(current)

    }


    class ActivityViewHolder(private var binding: ActivityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(activity: Activity) {
            binding.apply {
                activityDescriptionText.text = activity.activity
                activityTypeTextView.text = activity.type
            }

        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Activity>() {
            override fun areItemsTheSame(oldItem: Activity, newItem: Activity): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Activity, newItem: Activity): Boolean {
                return oldItem.activity == newItem.activity
            }
        }
    }
}