package com.tannerquesenberry.flickrbrowser

import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

internal const val FLICKR_QUERY = "FLICKR_QUERY"
internal const val PHOTO_TRANSER = "PHOTO TRANSFER"

open class BaseActivity : AppCompatActivity() {
    private val TAG = "BaseActivity"

    internal fun activateToolbar(enableHome: Boolean) {
        Log.d(TAG, ".activateToolbar")

        // Inflate the toolbar and put in place at the top of the screen
        var toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        //Auto add home button if told to
        supportActionBar?.setDisplayHomeAsUpEnabled(enableHome)
    }
}