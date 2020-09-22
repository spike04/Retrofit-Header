package com.example.sampleapicall

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleapicall.models.StaffResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.Main).launch {
            val response = ApiService().getStaffsDataAsync()
            response.enqueue(object : Callback<StaffResponse> {
                override fun onResponse(
                    call: Call<StaffResponse>,
                    response: Response<StaffResponse>
                ) {
                    if (response.isSuccessful) {
                        recyclerView.apply {
                            setHasFixedSize(true)
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = StaffAdapter(response.body()!!.data.staffs)
                        }
                    }
                }

                override fun onFailure(call: Call<StaffResponse>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}