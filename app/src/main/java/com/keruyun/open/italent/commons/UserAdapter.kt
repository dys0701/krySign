package com.keruyun.open.italent.commons

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.keruyun.open.italent.R
import com.keruyun.open.italent.api.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(private val data: List<User>
                  , private val itemClickListener: UserAdapter.onItemClickListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view, itemClickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as UserAdapter.UserViewHolder
        holder.bind(data.get(position))
    }

    interface onItemClickListener {
        fun onItemClickListener(user: User)
    }

    inner class UserViewHolder(val view: View, private val itemClickListener: UserAdapter.onItemClickListener)
        : RecyclerView.ViewHolder(view) {
        private val name = itemView.name
        fun bind(user: User) {
            name.text = user.nickName
            super.itemView.setOnClickListener { itemClickListener.onItemClickListener(user)}
        }
    }
}