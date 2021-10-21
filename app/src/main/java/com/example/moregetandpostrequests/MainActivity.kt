package com.example.moregetandpostrequests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var adapter: RV
    lateinit var rv: RecyclerView
    lateinit var get: Button
    lateinit var input: EditText
    lateinit var add: Button

    var names = ArrayList<Name>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiInterface = APIClient.getClient()?.create(APIInterface::class.java)


        rv = findViewById(R.id.rv)


        get = findViewById(R.id.btnGet)
        input = findViewById(R.id.etInput)
        add =  findViewById(R.id.btnAdd)

        get.setOnClickListener {
            if(names.isNotEmpty()){
                names.clear()
            }
            val call: Call<ArrayList<Name>> = apiInterface!!.getNames()

            call.enqueue(object : Callback<ArrayList<Name>?>{
                override fun onResponse(
                    call: Call<ArrayList<Name>?>,
                    response: Response<ArrayList<Name>?>
                ) {
                    names = response.body()!!
                    adapter = RV(names)
                    rv.adapter = adapter
                    rv.layoutManager = LinearLayoutManager(this@MainActivity)
                }

                override fun onFailure(call: Call<ArrayList<Name>?>, t: Throwable) {
                    Toast.makeText(this@MainActivity,"Error", Toast.LENGTH_LONG).show()
                }

            })
        }

        add.setOnClickListener {
            var done = 1
            if(input.text.isNotEmpty()){
                val names = input.text.toString()
                if(names.contains(",")){
                    val namesList = names.split(",")
                    for (name in namesList){
                        val n = Name()
                        n.name = name
                        n.location = "KSA"

                        apiInterface!!.addName(n).enqueue(object : Callback<Name>{
                            override fun onResponse(call: Call<Name>, response: Response<Name>) {
                                Toast.makeText(this@MainActivity,"Added-${++done}", Toast.LENGTH_LONG).show()
                            }

                            override fun onFailure(call: Call<Name>, t: Throwable) {
                                Toast.makeText(this@MainActivity,"Error", Toast.LENGTH_LONG).show()
                            }

                        })
                    }
                    input.text.clear()
                    done = 0

                }
            }else{
                Toast.makeText(this@MainActivity,"Type Names", Toast.LENGTH_LONG).show()
            }
        }

    }
}