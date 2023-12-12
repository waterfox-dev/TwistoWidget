package com.waterfox.twistowidget

import android.app.Service
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.waterfox.twistowidget.apisearcher.ServiceBuilder
import com.waterfox.twistowidget.apisearcher.StopService
import com.waterfox.twistowidget.apisearcher.result.StopResult
import com.waterfox.twistowidget.apisearcher.result.StopSpecific
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultActivity : AppCompatActivity()
{
    private lateinit var queryString:String;
    private lateinit var resultList:ListView;
    private lateinit var stopNames: ArrayList<String>;
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        this.queryString = intent.getStringExtra("QueryString").toString();
        initComponent();
        loadResult();
    }

    private fun loadResult() {
        val stopService = ServiceBuilder.buildService(StopService::class.java);
        val requestCall = stopService.getStops("stop_name like '%${this.queryString}%'", 20);
        println(requestCall.request().url());
        requestCall.enqueue(object : Callback<StopResult> {
            override fun onResponse(call: Call<StopResult>, response: Response<StopResult>) {
                if (response.isSuccessful) {
                    val stopResult = response.body();
                    if (stopResult != null) {
                        for (r: StopSpecific in stopResult.results) {
                            stopNames.add(r.stop_name);
                        }
                        val adapter = ArrayAdapter(this@ResultActivity, android.R.layout.simple_list_item_1, stopNames);
                        resultList.adapter = adapter
                    }
                } else {
                    val text = "Sorry, but the request failed";
                    val duration = Toast.LENGTH_SHORT

                    val toast = Toast.makeText(this@ResultActivity, text, duration);
                    toast.show();
                }
            }

            override fun onFailure(call: Call<StopResult>, t: Throwable) {
            }
        })
    }

    private fun initComponent()
    {
        this.resultList = findViewById<ListView>(R.id.result_activity_result_list);
        this.stopNames = ArrayList();
    }

}

