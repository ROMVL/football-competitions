package com.romanik.footballcompetitions.presentation.core.extentions

import android.view.View
import androidx.fragment.app.Fragment
import com.romanik.footballcompetitions.R
import com.romanik.footballcompetitions.presentation.core.platform.BaseFragment
import com.romanik.footballcompetitions.presentation.ui.main.MainActivity
import com.romanik.footballcompetitions.presentation.ui.mainflow.MainFlowFragment
import kotlinx.android.synthetic.main.activity_main.*

val BaseFragment.viewContainer: View get() = (activity as MainActivity).hostMobileFragment.view!!

val BaseFragment.rootFragment: MainFlowFragment? get() {
    return (activity?.supportFragmentManager
        ?.findFragmentById(R.id.hostMobileFragment)
        ?.childFragmentManager
        ?.fragments
        ?.get(0) as? MainFlowFragment)
}

val Fragment.mainActivity: MainActivity? get() = (activity as? MainActivity)