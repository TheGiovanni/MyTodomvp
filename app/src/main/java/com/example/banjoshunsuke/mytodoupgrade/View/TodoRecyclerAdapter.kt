package com.example.banjoshunsuke.mytodoupgrade.View

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.banjoshunsuke.mytodoupgrade.Model.TodoEntity
import com.example.banjoshunsuke.mytodoupgrade.R
import java.text.SimpleDateFormat


/**
 * Created by banjousyunsuke on 2018/02/15.
 */
class TodoRecyclerAdapter(private val context:Context, private val itemClickListener: OnItemClickListener, private val mList: List<TodoEntity>) : RecyclerView.Adapter<TodoRecyclerAdapter.ViewHolder>() {
    val sdf = SimpleDateFormat("yyyy/MM/dd")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoRecyclerAdapter.ViewHolder{
        val layoutInflater = LayoutInflater.from(context)
        val mView = layoutInflater.inflate(R.layout.list_item,parent,false)
        return ViewHolder(mView)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.let {
            holder.titleView.text = mList[position].todo
            holder.dateView.text = sdf.format(mList[position].deadLine)
            holder.cardView.setOnClickListener{
                itemClickListener.onItemClick(position)
            }
        }
    }


    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView:TextView = itemView.findViewById(R.id.list_title)
        val dateView:TextView = itemView.findViewById(R.id.list_date)
        val cardView: CardView = itemView.findViewById(R.id.list_card)

    }
    interface OnItemClickListener {
        fun onItemClick(todoId: Int)
    }
}