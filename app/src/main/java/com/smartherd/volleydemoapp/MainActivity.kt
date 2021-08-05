package com.smartherd.volleydemoapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var currentImageUrl:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadmeme()
    }
    private fun loadmeme(){

// Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"


// Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url,null,

            { response ->
                val currentImageUrl = response.getString("url")

                Glide.with(this).load(currentImageUrl).into(findViewById(R.id.image))
            },
            {

            })

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }

    fun share(view: View) {
            val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plane"
            val chooser = Intent.createChooser(intent,"Share this meme using")
        startActivity(chooser)
    }
    fun next(view: View) {
        loadmeme()
    }
}

