package com.example.marvelcharacterapp.network

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.marvelcharacterapp.model.BaseResponse
import com.example.marvelcharacterapp.model.Character
import com.example.marvelcharacterapp.model.Comic
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

const val MARVEL_PUBLIC_KEY = "write you marvel public key"
const val MARVEL_PRIVATE_KEY = "write you marvel private key"
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

    fun getCharacterComicList(serviceCallListener: ServiceCallListener<List<Comic>, Throwable>, characterId: String) {
        marvelApi.getCharacterComics(characterId, authQueryMap).enqueue(object: Callback<BaseResponse<Comic>?> {
            override fun onFailure(call: Call<BaseResponse<Comic>?>, t: Throwable) {
                serviceCallListener.failure?.invoke(t)
            }

            override fun onResponse(
                call: Call<BaseResponse<Comic>?>,
                response: Response<BaseResponse<Comic>?>
            ) {
                Log.i("getCharacterComicList", " body is ${response.body()}")
                serviceCallListener.success?.invoke(response.body()?.data?.results)
            }
        })
    }

}
