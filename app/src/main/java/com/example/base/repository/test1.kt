package com.example.base.repository

import android.util.Log

class test1 : testInterface {
    override fun getData() {
        Log.d("huan123", "get data")
    }

    override fun clickItem(int: Int) {
        Log.d("huan123", "click item $int")
    }

    override fun returnValue(): Int {
        return 10 - 5
    }
}