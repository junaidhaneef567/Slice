package com.jun.slice.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jun.slice.*
import com.jun.slice.ViewModel.MainViewModel
import com.jun.slice.ViewModel.ViewModelFactory
import com.jun.slice.model.Networking.ApiHelper
import com.jun.slice.model.Networking.RetrofitClient
import com.jun.slice.model.data_model.Tweet_data
import com.jun.slice.utils.Status
import com.jun.slice.utils.hideKeyboard
import com.jun.slice.utils.toast
import com.jun.slice.view.Adapters.TweetsListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: TweetsListAdapter
    var Newtweetslist:ArrayList<Tweet_data> = ArrayList()
    var tweetslist:List<Tweet_data> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        text_et.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count>=3)
                {
                    filtertweets(s.toString())
                }else if(count==0)
                {
                    retrieveList(tweetslist)
                }
            }
        })

        search_bt.setOnClickListener {
            val search_text = text_et.text.toString()
            if (search_text.length >= 3) {
                filtertweets(search_text)
                hideKeyboard()
            } else {
                hideKeyboard()
                toast("enter minimum 3 letter")
            }
        }

        setupViewModel() //view model setup
        setupUI()  //recyclerview setup to view categories in grid
        setupObservers() //observing data
    }


    private fun setupViewModel() {

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitClient.api))
        ).get(MainViewModel::class.java)

    }

    private fun setupUI() {

        tweets_rv.layoutManager= StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        adapter= TweetsListAdapter(arrayListOf())
        tweets_rv.adapter=adapter
    }

    private fun setupObservers() {

        viewModel.getTweets().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Log.d("sucesssss","Sucesss")
                        resource.data?.let {
                            tweetslist=it
                            retrieveList(tweetslist)
                        }
                    }
                    Status.ERROR -> {
                        Log.d("errrr","errrrrrr")
                        toast(it.message.toString())
                    }
                    Status.LOADING -> {
                        Log.d("loadddd","loaaaad")
                    }
                }
            }
        })
    }


    private fun retrieveList(tweetsdata:List<Tweet_data>) {
        adapter.apply {
            addTweets(tweetsdata)
            notifyDataSetChanged()
        }
    }

    private fun filtertweets(searchText: String)
    {
        Newtweetslist.clear()
        for(i in 0 until tweetslist.size)
        {
            if(tweetslist[i].name.toLowerCase().contains(searchText.toLowerCase()) or
                tweetslist[i].handle.toLowerCase().contains(searchText.toLowerCase()) or
                tweetslist[i].text.toLowerCase().contains(searchText.toLowerCase()))
            {
                Newtweetslist.add(tweetslist[i])
            }
        }

        if(Newtweetslist.size>0) {
            search_empty.visibility=View.GONE
        }else
        {
            search_empty.visibility=View.VISIBLE
            toast("No tweets found")
        }
        retrieveList(Newtweetslist)
    }
}
