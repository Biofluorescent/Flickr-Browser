package com.tannerquesenberry.flickrbrowser

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import androidx.preference.PreferenceManager

class SearchActivity : BaseActivity() {
    private val TAG = "SearchActivity"
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, ".onCreate: starts")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        activateToolbar(true)
        Log.d(TAG, ".onCreate: ends")

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Log.d(TAG, ".onCreateOptionsMenu starts")
        menuInflater.inflate(R.menu.menu_search, menu)

        //Get a search manager object
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        //Reference to search menu item widget on toolbar
        searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        //Get searchable info from this activity. use componentName not this
        val searchableInfo = searchManager.getSearchableInfo(componentName)
        searchView?.setSearchableInfo(searchableInfo)

        searchView?.isIconified = false
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d(TAG, ".onQueryTextSubmit called")

                //Store search query for main activity to get access to
                val sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
                sharedPref.edit().putString(FLICKR_QUERY, query).apply()
                //Because of external keyboard possibility
                searchView?.clearFocus()

                finish()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        //Occurs when search is cancelled
        searchView?.setOnCloseListener {
            finish()
            false
        }

        Log.d(TAG, ".onCreateOptionsMenu returning")
        return true
    }
}