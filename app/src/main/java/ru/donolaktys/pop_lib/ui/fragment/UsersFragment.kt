package ru.donolaktys.pop_lib.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.donolaktys.pop_lib.databinding.FragmentUsersBinding
import ru.donolaktys.pop_lib.mvp.presenter.UsersPresenter
import ru.donolaktys.pop_lib.mvp.view.IUsersView
import ru.donolaktys.pop_lib.ui.App
import ru.donolaktys.pop_lib.ui.BackButtonListener
import ru.donolaktys.pop_lib.ui.adapter.UsersRvAdapter

class UsersFragment: MvpAppCompatFragment(), IUsersView, BackButtonListener {

    lateinit var binding: FragmentUsersBinding

    companion object{
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter().apply { App.component.inject(this) }
    }

    var adapter : UsersRvAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun init() {
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        adapter = UsersRvAdapter(presenter.usersListPresenter)
        binding.rvUsers.adapter = adapter
    }

    override fun updateUsersList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()
}