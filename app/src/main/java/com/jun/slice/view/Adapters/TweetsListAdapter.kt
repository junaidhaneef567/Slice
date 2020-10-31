package com.jun.slice.view.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jun.slice.R
import com.jun.slice.model.data_model.Tweet_data
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.tweet_list_item.view.*


class TweetsListAdapter(private val tweets:ArrayList<Tweet_data>): RecyclerView.Adapter<TweetsListAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder=
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tweet_list_item, parent, false))


    override fun getItemCount(): Int = tweets.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(tweets.get(position))

    }

    fun addTweets(tweetsData:List<Tweet_data>) {
        this.tweets.apply {
            clear()
            addAll(tweetsData)
        }

    }

    class DataViewHolder(itemview:View): RecyclerView.ViewHolder(itemview){

        fun bind(tweetdata: Tweet_data) {

            itemView.apply {
                Glide.with(profile_iv).load(tweetdata.profileImageUrl).placeholder(R.drawable.ic_person).into(profile_iv)

//                Picasso.get().load(tweetdata.profileImageUrl).into(profile_iv)

                name_tv.text=tweetdata.name

                handle_tv.text=tweetdata.handle

                count_tv.text=tweetdata.favoriteCount.toString()

                retweet_tv.text=tweetdata.retweetCount.toString()

                text_tv.text=tweetdata.text
            }

        }
    }
}