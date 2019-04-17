package markod.irails.mvp

import androidx.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
open class BasePresenter<T : MvpView> : MvpPresenter<T> {

    var mvpView: T? = null
        private set

    val subscriptions: CompositeDisposable = CompositeDisposable()

    val isViewAttached: Boolean
        get() = mvpView != null

    override fun attachView(mvpView: T) {
        this.mvpView = mvpView
    }

    @CallSuper
    override
            /**
             * If overriding make sure super.detachView() is called on te end.
             */
    fun detachView() {
        subscriptions.clear()
        mvpView = null
    }

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    class MvpViewNotAttachedException : RuntimeException("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter")
}