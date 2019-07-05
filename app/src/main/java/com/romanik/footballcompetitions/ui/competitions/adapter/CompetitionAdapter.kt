package com.romanik.footballcompetitions.ui.competitions.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR.competition
import com.romanik.footballcompetitions.core.platform.DataBindingViewHolder
import com.romanik.footballcompetitions.databinding.CompetitionItemRowBinding
import com.romanik.footballcompetitions.domain.model.Competition

class CompetitionAdapter(
    private val onClickListener: OnClickListener
) : androidx.recyclerview.widget.RecyclerView.Adapter<CompetitionAdapter.SimpleHolder>() {

    val TAG = CompetitionAdapter::class.java.simpleName
    var items: List<Competition> = arrayListOf()

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SimpleHolder, position: Int) {
        Log.d(TAG, "bind, position = $position")
        holder.onBind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleHolder {
        val binding = CompetitionItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SimpleHolder(binding)
    }

    inner class SimpleHolder(dataBinding: ViewDataBinding)
        : DataBindingViewHolder<Competition>(dataBinding)  {
        override fun onBind(t: Competition): Unit = with(t) {
            dataBinding.setVariable(competition, t)
            dataBinding.root.setOnClickListener { onClickListener.onClickItem(t) }
        }
    }

    interface OnClickListener {
        fun onClickItem(competition: Competition)
    }
}