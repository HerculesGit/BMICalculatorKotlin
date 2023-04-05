package com.herco.bmicalculatorkotlin.shared.utils

import android.content.Context
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.herco.bmicalculatorkotlin.R


class ViewUtils {
    companion object {
        fun removeWhiteCorners(context: Context, view: View) {
            view.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
        }

        fun setAppBar(activity: AppCompatActivity) {
            activity.supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            activity.supportActionBar?.setCustomView(R.layout.app_bar)

            activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            activity.supportActionBar?.setIcon(R.drawable.ic_arrow_back)


            if (activity.isTaskRoot) {
                activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
                val viewFake: View = activity.findViewById(R.id.view_back_fake)
                viewFake.visibility = View.GONE
            }
        }
    }
}