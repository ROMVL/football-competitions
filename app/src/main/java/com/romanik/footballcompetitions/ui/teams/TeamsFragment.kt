package com.romanik.footballcompetitions.ui.teams

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.romanik.footballcompetitions.R
import com.romanik.footballcompetitions.core.platform.BaseFragment
import com.romanik.footballcompetitions.ui.teams.adapter.TeamAdapter
import kotlinx.android.synthetic.main.teams_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TeamsFragment : BaseFragment() {

    override val actionText: Int = R.string.try_again

    override val actionForNotify = { viewModel.doIt() }

    override fun disableIfLoading(enable: Boolean) { }

    companion object {
        fun newInstance() = TeamsFragment()
    }

    override fun layoutId(): Int = R.layout.teams_fragment

    override val viewModel: TeamsViewModel by viewModel()

    private val adapter : TeamAdapter by lazy { TeamAdapter() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setMenuIconForToolBar()
        initializeRv()
        with(viewModel) {
            viewModel.teams.observe(this@TeamsFragment, Observer {
                adapter.items = it
                adapter.notifyDataSetChanged()
            })
        }
    }

    private fun initializeRv() {
        val layoutManager = LinearLayoutManager(context as Context)
        val divider = DividerItemDecoration(rvTeams?.context, layoutManager.orientation)
        rvTeams?.adapter = adapter
        rvTeams?.layoutManager = layoutManager
        rvTeams?.addItemDecoration(divider)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (view != null) Navigation.findNavController(view!!).navigateUp() else super.onOptionsItemSelected(item)
    }

}
