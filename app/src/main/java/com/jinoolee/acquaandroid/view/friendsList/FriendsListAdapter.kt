package com.jinoolee.acquaandroid.view.friendsList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jinoolee.acquaandroid.databinding.FriendItemBinding
import com.jinoolee.acquaandroid.view.BindableAdapter
import com.jinoolee.acquaandroid.viewmodel.FriendItemViewModel

class FriendsListAdapter : RecyclerView.Adapter<FriendsListAdapter.FriendHolder>(), BindableAdapter<List<FriendItemViewModel>> {

    private var friendItems = emptyList<FriendItemViewModel>()

    override fun getItemCount() = friendItems.size

    override fun setData(data: List<FriendItemViewModel>) {
        friendItems = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FriendItemBinding.inflate(inflater, parent, false)
        return FriendHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendHolder, position: Int) {
        holder.bind(friendItems[position])
//        RxView.clicks(holder.itemView)
//                .map { _ -> holder.}
    }

    inner class FriendHolder(private val binding: FriendItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(friend: FriendItemViewModel) {
            binding.friendItemViewModel = friend
            binding.executePendingBindings()
        }
    }
}