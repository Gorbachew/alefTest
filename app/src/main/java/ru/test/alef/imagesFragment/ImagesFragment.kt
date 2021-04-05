package ru.test.alef.imagesFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_images.*
import ru.lifehack.test.adapter.RecyclerViewAdapter
import ru.test.alef.MainActivity
import ru.test.alef.R


class ImagesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_images, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onStart() {
        super.onStart()
        val cont = context as MainActivity

        getImages(cont)
        refreshList(cont)
    }

    @SuppressLint("CheckResult")
    private fun getImages(cont: MainActivity){
        cont.viewModel.getImages()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("TEST", "onNext")
                val gridLayoutManager = GridLayoutManager(
                    context,
                    detectSpan(),
                    GridLayoutManager.VERTICAL,
                    false
                )
                rvImages.layoutManager = gridLayoutManager
                rvImages.adapter = RecyclerViewAdapter(cont, it)
            }, {})
    }

    private fun detectSpan(): Int{
        val display = (context as MainActivity).windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)

        val density = resources.displayMetrics.density
        val dpWidth = outMetrics.widthPixels / density
        return Math.round(dpWidth / 250)
    }

    private fun refreshList(cont: MainActivity){
        swipe_refresh.setOnRefreshListener {
            Toast.makeText(context, getString(R.string.update), Toast.LENGTH_SHORT).show()
            getImages(cont)
            swipe_refresh.isRefreshing = false
        }
    }
}