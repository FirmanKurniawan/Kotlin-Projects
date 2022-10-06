package com.haypp.githubuser

import android.app.SearchManager
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.haypp.githubuser.adaptor.ListUserAdaptor
import com.haypp.githubuser.api.*
import com.haypp.githubuser.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var bindingg: ActivityMainBinding
    private val listDataUtama = ArrayList<Items>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findUser("haypp")
        bindingg = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingg.root)
        bindingg.rvgithub.setHasFixedSize(true)
    }

    private fun showRecyclerList() {
        bindingg.rvgithub.layoutManager = LinearLayoutManager(this)
        val listUserAdaptor = ListUserAdaptor(listDataUtama)
        bindingg.rvgithub.adapter = listUserAdaptor
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchView.clearFocus()
                findUser(query)
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuFavorite -> {
                val i = Intent(this, FavoriteActivity::class.java)
                startActivity(i)
                return true
            }
            R.id.menuSettings -> {
                val i = Intent(this, SettingsActivity::class.java)
                startActivity(i)
                return true
            }
            else -> return true
        }
    }

    private fun findUser(username: String) {
        showLoading(true)
        ApiConfig.getApiService().getSearchUser(username).enqueue(object : Callback<ListDataProfile> {
            override fun onResponse(call: Call<ListDataProfile>, response: Response<ListDataProfile>) {
                if (response.isSuccessful) {
                    showLoading(false)
                    val listItems = response.body()
                    if (listItems != null) {
                        listDataUtama.clear()
                        responseUser(listItems.items)
                    }
                }
            }
            override fun onFailure(call: Call<ListDataProfile>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                showLoading(false)
            }
        })
    }
    private fun responseUser(items: ArrayList<Items>) {
        for(mitems in items){
            listDataUtama.add(Items(mitems.avatarUrl, mitems.login))
        }
        showRecyclerList()
    }
    private fun showLoading(isLoading: Boolean) {
        val progressBar = findViewById<ProgressBar>(R.id.barprogess)
        progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}



