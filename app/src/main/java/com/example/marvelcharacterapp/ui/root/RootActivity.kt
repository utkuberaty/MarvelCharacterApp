package com.example.marvelcharacterapp.ui.root

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelcharacterapp.R
import com.example.marvelcharacterapp.base.OnBackPressListener
import com.example.marvelcharacterapp.ui.characterList.CharacterListFragment

class RootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.root_activity)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CharacterListFragment.newInstance())
                .commitNow()
        }
    }

    override fun onBackPressed() {
        with(supportFragmentManager.fragments.last()){
            if(this is OnBackPressListener) this.onBackPress()
        }
    }
}
