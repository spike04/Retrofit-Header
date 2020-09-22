package com.example.sampleapicall

import com.example.sampleapicall.models.StaffResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://admin.horanepal.com/api/v1/"
private const val STAFFS = "staffs"

interface ApiService {

    @GET(STAFFS)
    fun getStaffsDataAsync(): Call<StaffResponse>

    companion object {
        operator fun invoke(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getHeader())
                .build()
                .create(ApiService::class.java)
        }

        private fun getHeader(): OkHttpClient {
            val client = OkHttpClient.Builder()
            client.networkInterceptors().add(
                Interceptor { chain ->
                    val request = chain.request().newBuilder()
                        .header("apikey", "4jcc0bhjirk0g4k8c00kw4s4sss40kk")
                        .build()
                    chain.proceed(request)
                }
            )
            return client.build()
        }
    }
}