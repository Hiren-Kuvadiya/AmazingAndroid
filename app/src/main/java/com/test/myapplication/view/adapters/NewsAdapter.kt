package com.test.myapplication.view.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.test.myapplication.R
import com.test.myapplication.model.Artical

class NewsAdapter(var context: Context) :

    RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    private var TAG = javaClass.simpleName
    var newslist: List<Artical>

    init {
        newslist = ArrayList()
    }

    fun addData(arrList: List<Artical>) {
        this.newslist = arrList
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var news_iv: ImageView = view.findViewById(R.id.news_iv)
        var news_title: TextView = view.findViewById(R.id.news_title)
        var news_by_tv: TextView = view.findViewById(R.id.news_by_tv)

    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var m_news = newslist.get(position)

        Glide.with(context).load(m_news.urlToImage).into(holder.news_iv)
        holder.news_title.text = m_news.title
        holder.news_by_tv.text = m_news.author


    }

    override fun getItemCount(): Int {
        return newslist.size
    }

}