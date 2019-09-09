package com.romanik.footballcompetitions.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.romanik.footballcompetitions.R


class MainActivity : AppCompatActivity() {

    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        if (navController?.popBackStack() == false) {
            super.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp() = findNavController(R.id.hostMobileFragment).navigateUp()

}
