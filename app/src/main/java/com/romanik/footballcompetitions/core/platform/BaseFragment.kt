package com.romanik.footballcompetitions.core.platform

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.romanik.footballcompetitions.R
import com.romanik.footballcompetitions.core.extentions.mainActivity
import com.romanik.footballcompetitions.core.extentions.rootFragment
import com.romanik.footballcompetitions.core.extentions.viewContainer
import com.romanik.footballcompetitions.ui.main.MainActivity
import com.romanik.footballcompetitions.ui.mainflow.MainFlowFragment
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseFragment : Fragment() {

    abstract fun layoutId(): Int

    abstract val viewModel: BaseViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutId(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavController(findNavController())
        toOpenDrawer()
        with(viewModel) {
            error.observe(this@BaseFragment, Observer {
                notifyWithAction(
                    it?.message ?: resources.getString(R.string.retry_later),
                    resources.getString(actionText),
                    actionForNotify
                )
            })

            loading.observe(this@BaseFragment, Observer {
                showLoading(it.getContentIfNotHandled() ?: false)
                disableIfLoading(!(it.getContentIfNotHandled() ?: false))
            })
        }
    }

    abstract val actionText: Int

    abstract val actionForNotify: () -> Any

    abstract fun disableIfLoading(enable: Boolean)

    private fun showLoading(show: Boolean) {
        rootFragment?.getProgressBar()?.visibility = if (show) View.VISIBLE else View.GONE
    }

    fun setMenuIconForToolBar() {
        mainActivity?.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    fun toOpenDrawer(toOpen: Boolean = true) {
        rootFragment?.toOpenTheDrawer = toOpen
    }

    private fun setNavController(navController: NavController?) {
        mainActivity?.navController = navController
        rootFragment?.navController = navController
    }

    internal fun notify(message: String) =
        Snackbar.make(viewContainer, message, Snackbar.LENGTH_LONG).show()

    internal fun notifyWithAction(message: String, actionText: String, action: () -> Any) {
        val snackBar = Snackbar.make(viewContainer, message, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction(actionText) { _ -> action.invoke() }
        snackBar.setActionTextColor(
            ContextCompat.getColor(context as Context, R.color.colorPrimary))
        snackBar.show()
    }

}