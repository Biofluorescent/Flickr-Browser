package com.tannerquesenberry.flickrbrowser

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView

// Context for gesture detector to work, reference to recycler we detect taps on, and listener for callbacks
class RecyclerItemClickListener(context: Context, recyclerView: RecyclerView, private val listener: OnRecyclerClickListener)
    : RecyclerView.SimpleOnItemTouchListener() {

    private val TAG = "RecyclerItemClickListen"

    interface OnRecyclerClickListener {
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

    // This gesture class is compatible with previous android versions
    private val gestureDetector = GestureDetectorCompat(context, object : GestureDetector.SimpleOnGestureListener() {

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            Log.d(TAG, ".onSingleTapUp: starts")

            // Uses the coordinates of the tap and retrieves the view at the location
            val childView = recyclerView.findChildViewUnder(e.x, e.y)

            Log.d(TAG, ".onSingleTapUp: calling listener.onItemClick")
            if (childView != null) {
                listener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView))
            }
            return true
        }

        override fun onLongPress(e: MotionEvent) {
            Log.d(TAG, ".onLongPress: starts")

            // Uses the coordinates of the tap and retrieves the view at the location
            val childView = recyclerView.findChildViewUnder(e.x, e.y)

            Log.d(TAG, "..onLongPress: calling listener.onItemLongClick")
            if (childView != null) {
                listener.onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView))
            }
        }
    })

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        Log.d(TAG, ".onInterceptTouchEvent: starts $e")
        val result = gestureDetector.onTouchEvent(e)
//        return super.onInterceptTouchEvent(rv, e)
        return result
    }
}