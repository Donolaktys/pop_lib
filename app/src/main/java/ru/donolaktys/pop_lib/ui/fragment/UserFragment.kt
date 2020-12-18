package ru.donolaktys.pop_lib.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.donolaktys.pop_lib.databinding.FragmentUserBinding
import ru.donolaktys.pop_lib.mvp.model.entity.GithubUser
import ru.donolaktys.pop_lib.mvp.presenter.UserPresenter
import ru.donolaktys.pop_lib.mvp.view.IUserView
import ru.donolaktys.pop_lib.ui.App
import ru.donolaktys.pop_lib.ui.BackButtonListener
import ru.donolaktys.pop_lib.ui.adapter.ReposRvAdapter

class UserFragment: MvpAppCompatFragment(), IUserView, BackButtonListener {

    private val USER_KEY: String = "user_key_123"
    lateinit var binding: FragmentUserBinding

    companion object{
        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_KEY, user)
            }
        }
    }

    private val presenter: UserPresenter by moxyPresenter {
        val user = (arguments?.get(USER_KEY) as GithubUser)
        UserPresenter(user).apply { App.component.inject(this) }
    }

    var adapter: ReposRvAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun setLogin(login: String) {
        binding.userTvLogin.text = "$login repositories:"
    }

    override fun init() {
        binding.userRvRepos.layoutManager = LinearLayoutManager(requireContext())
        adapter = ReposRvAdapter(presenter.reposListPresenter)
        binding.userRvRepos.adapter = adapter
    }

    override fun updateReposList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()

}
