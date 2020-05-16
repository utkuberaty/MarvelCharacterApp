package com.example.marvelcharacterapp.network

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.marvelcharacterapp.model.BaseResponse
import com.example.marvelcharacterapp.model.Character
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

const val MARVEL_PUBLIC_KEY = "50c169f4c5337f36f5eeeb2c941d1d06"
const val MARVEL_PRIVATE_KEY = "ffaa7d2cbd1a67134af8fe101e390c7eb9efb9aa"
const val API_KEY= "apikey"
const val TIME = "ts"
const val HASH = "hash"

const val List_Page_Limit = 30

class MarvelRepository: KoinComponent {
    private val retrofitService: RetrofitService by inject()

    private val marvelApi: MarvelApi

    init { marvelApi = retrofitService.getService() }

    private val authQueryMap: Map<String,String>

    get() {
        val timeInMillis = Calendar.getInstance().timeInMillis.toString()
       return mapOf(
            API_KEY to MARVEL_PUBLIC_KEY,
            TIME to timeInMillis,
            HASH to retrofitService.md5(timeInMillis + MARVEL_PRIVATE_KEY + MARVEL_PUBLIC_KEY)
        )
    }
//    @Query("apiKey") ,@Query("limit") limit: Int, @Query("offset") offset: Int

    fun getCharacterList(serviceCallListener: ServiceCallListener<List<Character>, Throwable>, offset: Int){
        val queryMap = mapOf("limit" to List_Page_Limit, "offset" to  offset )
        marvelApi.getCharacterList(authQueryMap, queryMap).enqueue(object : Callback<BaseResponse<Character>> {
            override fun onFailure(call: Call<BaseResponse<Character>>, t: Throwable) {
                serviceCallListener.failure?.invoke(t)
            }

            override fun onResponse(
                call: Call<BaseResponse<Character>>,
                response: Response<BaseResponse<Character>>
            ) {
                Log.i("getCharacterList", " response is ${response.body()}")
                Log.i("getCharacterList", " response is ${response.message()}")

                serviceCallListener.success?.invoke(response.body()?.data?.results)
            }
        })
    }

}