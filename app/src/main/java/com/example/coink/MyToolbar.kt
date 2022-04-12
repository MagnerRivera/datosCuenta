package com.example.coink

import androidx.appcompat.app.AppCompatActivity

class MyToolbar {
    fun show(activity: AppCompatActivity, title:String, upButton:Boolean){
        activity.setSupportActionBar(activity.findViewById(R.id.toolbar))
        activity.supportActionBar?.title = title
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(upButton)
    }
}