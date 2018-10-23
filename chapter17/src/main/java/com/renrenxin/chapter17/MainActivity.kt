package com.renrenxin.chapter17

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val a = "test kotlin"
        val b = 5
        val c: Double = 1.0
        val d: List<String> = ArrayList()

        System.out.println(a.javaClass)
        System.out.println(b.javaClass)
        System.out.println(c.javaClass)
        System.out.println(d.javaClass)
    }
}
