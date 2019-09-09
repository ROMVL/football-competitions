package com.romanik.footballcompetitions.presentation.ui.mainflow

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView

import com.romanik.footballcompetitions.R
import com.romanik.footballcompetitions.presentation.core.extentions.mainActivity
import kotlinx.android.synthetic.main.main_flow_fragment.*

class MainFlowFragment : Fragment() {

    companion object {
        fun newInstance() = MainFlowFragment()
    }

    private lateinit var viewModel: MainFlowViewModel

    var toOpenTheDrawer = true
    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_flow_fragment, container, false)
        setHasOptionsMenu(true)

        val navController = findNavController(view.findViewById(R.id.hostFragment))

        mainActivity?.apply {
            setSupportActionBar(view.findViewById<Toolbar>(R.id.toolBar))
            setupActionBarWithNavController(navController, view.findViewById<DrawerLayout>(R.id.drawerLayout))
            setupWithNavController(view.findViewById<NavigationView>(R.id.navView), navController)
        }

        navController.navigateUp()

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainFlowViewModel::class.java)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toOpenTheDrawer) {
            when (item.itemId) {
                android.R.id.home -> {
                    drawerLayout.openDrawer(GravityCompat.START)
                    return true
                }
            }
        }
        return if (navController != null) navController!!.popBackStack() else super.onOptionsItemSelected(item)

    }

    fun getProgressBar() = progressBar

}
