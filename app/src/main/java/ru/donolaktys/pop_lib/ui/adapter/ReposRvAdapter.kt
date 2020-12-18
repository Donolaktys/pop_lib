package ru.donolaktys.pop_lib.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import ru.donolaktys.pop_lib.R
import ru.donolaktys.pop_lib.mvp.presenter.list.IReposListPresenter
import ru.donolaktys.pop_lib.mvp.view.list.IRepoItemView

class ReposRvAdapter(val presenter: IReposListPresenter): RecyclerView.Adapter<ReposRvAdapter.ViewHolder>() {

    inner class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), IRepoItemView, LayoutContainer {
        override var pos: Int = -1

        override fun setRepoName(text: String) {
            containerView.findViewById<TextView>(R.id.tv_repos).text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repos, parent, false)).apply {
            containerView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
    }

    override fun getItemCount(): Int = presenter.getCount()
}