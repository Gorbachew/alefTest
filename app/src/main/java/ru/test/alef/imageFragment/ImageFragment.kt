package ru.test.alef.imageFragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.grid_view.*
import ru.test.alef.MainActivity
import ru.test.alef.R
import ru.test.alef.api.PicassoInstance


class ImageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onStart() {
        super.onStart()
        val cont = context as MainActivity
        cont.viewModel.getImage()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    PicassoInstance(it, imageView).load()
                }


    }
}