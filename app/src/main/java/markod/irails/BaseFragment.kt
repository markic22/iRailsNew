package markod.irails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    lateinit var contentView: View

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        contentView = inflater.inflate(getLayoutId(), container, false)
        return contentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    abstract fun initUI()
}