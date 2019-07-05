package com.romanik.footballcompetitions.ui.competitiondetails

import android.os.Bundle

import com.romanik.footballcompetitions.R
import com.romanik.footballcompetitions.core.platform.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CompetitionDetailsFragment : BaseFragment() {

    override fun layoutId(): Int = R.layout.competition_details_fragment

    override val actionText: Int = R.string.try_again

    override val actionForNotify = { }

    override fun disableIfLoading(enable: Boolean) { }

    companion object {
        fun newInstance() = CompetitionDetailsFragment()
    }

    override val viewModel: CompetitionDetailsViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toOpenDrawer(false)
    }

}
