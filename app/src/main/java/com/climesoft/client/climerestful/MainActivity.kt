package com.climesoft.client.climerestful

import android.graphics.Movie
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mList = findViewById<RecyclerView>(R.id.rView)

        val todosList = this.getTodos()
        val adapter = AdapterTodos(applicationContext, todosList)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        val dividerItemDecoration = DividerItemDecoration(mList.context, linearLayoutManager.orientation)

        mList.setHasFixedSize(true)
        mList.layoutManager = linearLayoutManager
        mList.addItemDecoration(dividerItemDecoration)
        mList.adapter = adapter
    }

    private fun getTodos(): ArrayList<Todo>{
        val todosList = ArrayList<Todo>()
        val jsonArrayRequest =
            JsonArrayRequest("https://jsonplaceholder.typicode.com/todos/", object : Response.Listener<JSONArray?> {
                override fun onResponse(response: JSONArray?) {
                    for (i in 0 until response!!.length()) {
                        try {
                            val jsonObject = response.getJSONObject(i)
                            todosList.add(Todo(jsonObject.getString("name")))
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }
            }, Response.ErrorListener { })
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(jsonArrayRequest)
        return todosList;
    }
}
