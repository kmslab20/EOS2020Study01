package com.example.eos2020study01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_old.*
import kotlinx.android.synthetic.main.activity_main_old.imgBackground
import org.json.JSONException
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOldActivity.setOnClickListener {
            try {
                val intent = Intent(this, MainOldActivity::class.java)
                startActivity(intent)
            }catch (e: Exception){
                Log.d("ss", "sss")
            }

        }

        var requestQueue : RequestQueue = Volley.newRequestQueue(this)
        val url = "https://api.openweathermap.org/data/2.5/weather?q=Seoul&units=metric&appid=3f7daf86c5c93e8e3dbe82995b8a64c4"

        val request = JsonObjectRequest(Request.Method.GET, url, null,
        Response.Listener {
            response -> try{
                val weatherId: Int = response.getJSONArray("weather").getJSONObject(0).getInt("id")
                val temp: Double = response.getJSONObject("main").getDouble("temp")
                val weatherName: String;
                val humidity: Int = response.getJSONObject("main").getInt("humidity")
                val windSpeed: Double = response.getJSONObject("wind").getDouble("speed")
                val clouds: Int = response.getJSONObject("clouds").getInt("all")

            if(weatherId>=200 && weatherId<=232){
                weatherName="뇌우"
                imgBackground.setImageDrawable(resources.getDrawable(R.drawable.rainy02, applicationContext.theme))

            }else if(weatherId>=300 && weatherId<=321){
                weatherName="이슬비"
                imgBackground.setImageDrawable(resources.getDrawable(R.drawable.rainy04, applicationContext.theme))
            }else if(weatherId>=500 && weatherId<=531){
                weatherName="비"
                imgBackground.setImageDrawable(resources.getDrawable(R.drawable.rainy05, applicationContext.theme))
            }else if(weatherId>=600 && weatherId<=622){
                weatherName="눈"
                imgBackground.setImageDrawable(resources.getDrawable(R.drawable.snowy03, applicationContext.theme))
            }else if(weatherId==701 || weatherId==741){
                weatherName="안개"
                imgBackground.setImageDrawable(resources.getDrawable(R.drawable.cloudy02, applicationContext.theme))
            } else if(weatherId==800){
                weatherName="맑음"
                imgBackground.setImageDrawable(resources.getDrawable(R.drawable.sunny02, applicationContext.theme))
            }else if(weatherId>=801 && weatherId<=804){
                weatherName="흐림"
                imgBackground.setImageDrawable(resources.getDrawable(R.drawable.cloudy01, applicationContext.theme))
            }
            else{
                weatherName="Unknown"
            }

            txtBoxTemperature.text = temp.toString()+"℃"
            txtBoxWeather.text = weatherName
            txtDownHumidity.text = humidity.toString() +" %"
            txtDownWindSpeed.text = windSpeed.toString()+" m/s"
            txtDownCloud.text = clouds.toString()+ " %"
            txtRegion.text = response.getString("name") + ", " + response.getJSONObject("sys").getString("country")


        } catch (e: JSONException){

            }
        }, Response.ErrorListener {
                    error -> Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
        })


//        button01.setOnClickListener {
//            //change the text in textView01
//            textView01.text = "Sunny"
//            imgBackground.setImageDrawable(resources.getDrawable(R.drawable.sunny04, applicationContext.theme))
//        }
//
//        button02.setOnClickListener {
//            textView01.text = "Rainy"
//            imgBackground.setImageDrawable(resources.getDrawable(R.drawable.rainy02, applicationContext.theme))
//        }
//
//        button03.setOnClickListener {
//            textView01.text = "Cloudy"
//            imgBackground.setImageDrawable(resources.getDrawable(R.drawable.cloudy01, applicationContext.theme))
//        }
        requestQueue.add(request)

    }
}