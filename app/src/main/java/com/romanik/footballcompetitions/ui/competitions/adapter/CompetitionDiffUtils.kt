package com.romanik.footballcompetitions.ui.competitions.adapter

import androidx.recyclerview.widget.DiffUtil
import com.romanik.footballcompetitions.domain.model.Competition

class CompetitionDiffUtils(
    private val oldData: List<Competition>,
    private val newData: List<Competition>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldData[oldItemPosition]
        val newItem = newData[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldData[oldItemPosition]
        val newItem = newData[newItemPosition]
        return oldItem.currentSeason?.currentMatchDay == newItem.currentSeason?.currentMatchDay
                && oldItem.area == newItem.area
                && oldItem.name == newItem.name
                && oldItem.plan == newItem.plan
                && oldItem.currentSeason == newItem.currentSeason
    }
}