package com.example.marvelcharacterapp.network

import com.example.marvelcharacterapp.model.BaseResponse
import com.example.marvelcharacterapp.model.Character
import com.example.marvelcharacterapp.model.Comic
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MarvelApi {
//    @Query("apiKey") ,@Query("limit") limit: Int, @Query("offset") offset: Int
    @GET("/v1/public/characters")
    fun getCharacterList(@QueryMap callAuthQueryMap:Map<String, String>, @QueryMap callQueryMap: Map<String, Int>) : Call<BaseResponse<Character>>

    @GET("/v1/public/characters/{characterId}/comics")
    fun getCharacterComics(@Path("characterId") characterId: String, @QueryMap callAuthQueryMap:Map<String, String>) : Call<BaseResponse<Comic>>

}