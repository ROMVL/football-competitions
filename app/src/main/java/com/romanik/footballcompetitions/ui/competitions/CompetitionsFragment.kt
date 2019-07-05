package com.romanik.footballcompetitions.ui.competitions

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

import com.romanik.footballcompetitions.R
import com.romanik.footballcompetitions.core.platform.BaseFragment
import com.romanik.footballcompetitions.domain.model.Competition
import com.romanik.footballcompetitions.ui.competitions.adapter.CompetitionAdapter
import com.romanik.footballcompetitions.ui.competitions.adapter.CompetitionDiffUtils
import kotlinx.android.synthetic.main.competitions_fragment.*

class CompetitionsFragment : BaseFragment(), CompetitionAdapter.OnClickListener {

    override val actionText: Int = R.string.try_again

    override val actionForNotify = { viewModel.fetchCompetitions() }

    override fun disableIfLoading(enable: Boolean) { }

    override fun layoutId(): Int = R.layout.competitions_fragment

    companion object {
        fun newInstance() = CompetitionsFragment()
    }

    override val viewModel: CompetitionsViewModel by viewModel()

    private val adapter : CompetitionAdapter by lazy { CompetitionAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRv()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(viewModel) {
            fetchCompetitions()

            event.observe(this@CompetitionsFragment, Observer {
                when(it.content) {
                    CompetitionsViewModel.CompetitionsEvent.OBSERVE_DATA -> {
                        competitions.observe(this@CompetitionsFragment, Observer { data ->
                            val competitionDiffUtils = CompetitionDiffUtils(adapter.items, data)
                            val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(competitionDiffUtils)
                            adapter.items = data
                            diffResult.dispatchUpdatesTo(adapter)
                            Log.d("CompetitionsFragment", "data")
                        })
                    }
                }
            })

        }
    }

    private fun initializeRv() {
        val layoutManager = LinearLayoutManager(context as Context)
        val divider = DividerItemDecoration(rvCompetition?.context, layoutManager.orientation)
        rvCompetition?.adapter = adapter
        rvCompetition?.layoutManager = layoutManager
        rvCompetition?.addItemDecoration(divider)
    }

    override fun onClickItem(competition: Competition) {
        findNavController().navigate(R.id.action_competitionsFragment_to_competitionDetailsFragment)
    }

}
