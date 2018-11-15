package com.keruyun.open.italent

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.keruyun.open.italent.api.Sigin
import com.keruyun.open.italent.api.Token
import com.keruyun.open.italent.api.User
import com.keruyun.open.italent.commons.UserAdapter
import com.keruyun.open.italent.presenter.MainPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import javax.inject.Inject

class MainActivity : AppCompatActivity(), UserAdapter.onItemClickListener {
    private val TAG: String = "MainActivity"
    @Inject
    lateinit var presenter: MainPresenter
    private var users: List<User> = ArrayList()
    lateinit var userAdapter: UserAdapter
    private val disposable = CompositeDisposable()
    lateinit var token: Token
    override fun onItemClickListener(user: User) {
        disposable.add(presenter.getToken(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    disposable.add(presenter.sigin(it, user)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({
                                if (it.Code.equals("1")) {
                                    Toast.makeText(this, "打卡成功", Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(this, "打卡失败", Toast.LENGTH_SHORT).show()
                                }
                            },
                                    { error -> Log.e(TAG, "Unable to get username", error) }))
                },
                        { error -> Log.e(TAG, "Unable to get username", error) }))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ItalentApp.mainComponent.inject(this)
        setContentView(R.layout.activity_main)
        users = presenter.getUsers()
        userAdapter = UserAdapter(users, this)
        recyclerView.apply {
            val linearLayoutManager = LinearLayoutManager(context)
            layoutManager = linearLayoutManager
        }
        recyclerView.adapter = userAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}