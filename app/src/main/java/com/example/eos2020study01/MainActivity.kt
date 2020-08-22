package com.example.eos2020study01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button01.setOnClickListener {
            //change the text in textView01
            textView01.text = "Sunny"
            imgBackground.setImageDrawable(resources.getDrawable(R.drawable.sunny04, applicationContext.theme))
        }

        button02.setOnClickListener {
            textView01.text = "Rainy"
            imgBackground.setImageDrawable(resources.getDrawable(R.drawable.rainy02, applicationContext.theme))
        }

        button03.setOnClickListener {
            textView01.text = "Cloudy"
            imgBackground.setImageDrawable(resources.getDrawable(R.drawable.cloudy01, applicationContext.theme))
        }


    }
}