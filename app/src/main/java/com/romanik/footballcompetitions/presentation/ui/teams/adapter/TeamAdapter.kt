package com.romanik.footballcompetitions.presentation.ui.teams.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR.team
import com.romanik.footballcompetitions.presentation.core.platform.DataBindingViewHolder
import com.romanik.footballcompetitions.databinding.TeamItemRowBinding
import com.romanik.footballcompetitions.domain.model.Team

class TeamAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<TeamAdapter.SimpleHolder>() {

    var items: List<Team> = arrayListOf()

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SimpleHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleHolder {
        val binding  = TeamItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SimpleHolder(binding)
    }

    inner class SimpleHolder(dataBinding: ViewDataBinding)
        : DataBindingViewHolder<Team>(dataBinding)  {
        override fun onBind(t: Team): Unit = with(t) {
            dataBinding.setVariable(team, t)
        }
    }

}