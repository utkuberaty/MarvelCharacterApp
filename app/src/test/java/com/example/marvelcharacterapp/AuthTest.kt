package com.example.marvelcharacterapp

import android.util.Log
import com.example.marvelcharacterapp.di.networkModule
import com.example.marvelcharacterapp.di.viewModelModule
import com.example.marvelcharacterapp.network.MarvelRepository
import com.example.marvelcharacterapp.network.listen
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class AuthTest {

    private fun md5(input: String): String {
        try {
            // Create MD5 Hash
            val digest = MessageDigest.getInstance("MD5")
            digest.update(input.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = StringBuffer()
            for (i in messageDigest.indices) hexString.append(
                Integer.toHexString(
                    0xFF and messageDigest[i].toInt()
                )
            )
            println("hex is $hexString")
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }

    @Test
    fun test(){
        val hash = md5("dfsdfsdfsdfsdfsdfsdfsdfsdfs")
        if(hash != "")
            assert(true)
        else assert(false)
    }
}