package com.waterfox.twistowidget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView

class MainActivity : AppCompatActivity() {

    private lateinit var searchBar: SearchView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.initComponent();
    }

    private fun initComponent() {
        this.searchBar = findViewById<SearchView>(R.id.mainActivitySearchBar);
        this.searchBar.queryHint = getString(R.string.search_bar_description);
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(query: String?): Boolean
            {
                val intent = Intent(this@MainActivity, ResultActivity::class.java);
                intent.putExtra("QueryString", query);
                startActivity(intent);
                return true;
            }

            override fun onQueryTextChange(p0: String?): Boolean
            {
                return true;
            }
        })
    }
}