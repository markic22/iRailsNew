package com.example.network.mvp

interface MvpPresenter<in V : MvpView> {

    fun attachView(mvpView: V)

    fun detachView()
}