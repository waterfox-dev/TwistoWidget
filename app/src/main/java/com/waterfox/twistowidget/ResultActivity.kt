package com.waterfox.twistowidget

import android.app.Service
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.waterfox.twistowidget.apisearcher.ServiceBuilder
import com.waterfox.twistowidget.apisearcher.StopService
import com.waterfox.twistowidget.apisearcher.result.StopResult
import com.waterfox.twistowidget.apisearcher.result.StopSpecific
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultActivity : AppCompatActivity()
{
    private lateinit var queryString: String
    private lateinit var resultList: ScrollView
    private lateinit var stopNames: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var linearLayoutResults: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        this.queryString = intent.getStringExtra("QueryString").toString()
        initComponent()
        loadResult()
    }

    private fun loadResult() {
        val stopService = ServiceBuilder.buildService(StopService::class.java)
        val requestCall = stopService.getStops("stop_name like '%${this.queryString}%'", 20)
        println(requestCall.request().url())
        requestCall.enqueue(object : Callback<StopResult> {
            override fun onResponse(call: Call<StopResult>, response: Response<StopResult>) {
                if (response.isSuccessful) {
                    val stopResult = response.body()
                    if (stopResult != null) {
                        for (r: StopSpecific in stopResult.results) {
                            val b = Button(this@ResultActivity)
                            b.text = r.stop_name
                            linearLayoutResults.addView(b)
                        }
                    }
                } else {
                    val text = "Sorry, but the request failed"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(this@ResultActivity, text, duration)
                    toast.show()
                }
            }

            override fun onFailure(call: Call<StopResult>, t: Throwable) {
                // Handle failure if needed
            }
        })
    }

    private fun initComponent() {
        this.resultList = findViewById<ScrollView>(R.id.result_scroll_view)
        this.stopNames = ArrayList()
        this.linearLayoutResults = findViewById(R.id.linear_layout_results)
    }

}


