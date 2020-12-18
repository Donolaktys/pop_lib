package ru.donolaktys.pop_lib.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import ru.donolaktys.pop_lib.R
import ru.donolaktys.pop_lib.mvp.model.image.IImageLoader
import ru.donolaktys.pop_lib.mvp.presenter.list.IUsersListPresenter
import ru.donolaktys.pop_lib.mvp.view.list.IUserItemView
import javax.inject.Inject

class UsersRvAdapter(val presenter: IUsersListPresenter): RecyclerView.Adapter<UsersRvAdapter.ViewHolder>() {

    @Inject lateinit var imageLoader: IImageLoader<ImageView>

    inner class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), IUserItemView, LayoutContainer {
        override var pos: Int = -1

        override fun setLogin(text: String) {
            containerView.findViewById<TextView>(R.id.tv_login).text = text
        }

        override fun loadImage(url: String) {
            imageLoader.loadInto(url, containerView.findViewById<ImageView>(R.id.iv_image))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)).apply {
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