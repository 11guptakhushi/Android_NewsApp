package com.capgemini.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val newsList: List<News>, val onSelection: (Int)-> Unit): RecyclerView.Adapter<NewsAdapter.NewsHolder>() {
    inner class NewsHolder(inflated: View): RecyclerView.ViewHolder(inflated){
        val img: ImageView = inflated.findViewById(R.id.img)
        val txtTitle: TextView = inflated.findViewById(R.id.txtTitle)
        val txtDate: TextView = inflated.findViewById(R.id.txtDate)
        val txtAuthor: TextView = inflated.findViewById(R.id.txtAuthor)
        val txtDescription: TextView = inflated.findViewById(R.id.txtDescription)

        init{
            itemView.setOnClickListener{
                onSelection(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        //inflate item xml and return viewholder
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_news, parent, false)
        return NewsHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        //bind data to views
        val news = newsList[position]
        holder.txtTitle.text = news.title
        holder.txtAuthor.text = news.author
        holder.txtDescription.text = news.description
        holder.txtDate.text = news.publishedAt
        news.urlToImage?.let {
            Glide.with(holder.itemView).load(it).into(holder.img)
        }
    }
}