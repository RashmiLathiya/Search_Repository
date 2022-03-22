package com.tailor.test_search_repos.helper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.example.Items
import com.squareup.picasso.Picasso
import com.tailor.test_search_repos.R
import de.hdodenhof.circleimageview.CircleImageView

class RepositoriesAdapter(var responseList: List<Items>,val listener: OnItemClickListener) : RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        var ivProfile = view.findViewById<CircleImageView>(R.id.ivProfile)
        var tvRepoName = view.findViewById<TextView>(R.id.tvRepoName)
        var tvRepoDetail = view.findViewById<TextView>(R.id.tvRepoDetail)
        var tvLanguage = view.findViewById<TextView>(R.id.tvLanguage)
        fun bind(item: Items) {
            tvRepoName.text= item.name
            tvRepoDetail.text = item.description
            tvLanguage.text = "Language: "+item.language

            Picasso.get().load(item.owner?.avatarUrl).into(ivProfile)
        }
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.adapter_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        return holder.bind(responseList[position])
    }

    override fun getItemCount(): Int {
        return responseList.size
    }


    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}

