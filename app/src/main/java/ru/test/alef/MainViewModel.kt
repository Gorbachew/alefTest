package ru.test.alef

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.launch
import ru.test.alef.repository.Repository

class MainViewModel(private val repository: Repository?) : ViewModel(){

    private lateinit var imageUrl: String

    fun setImageUrl(url: String){
        this.imageUrl = url
    }

    fun getImages(): Single<List<String>>{
        return Single.create { subscriber ->
            viewModelScope.launch {
                try{
                    subscriber.onSuccess(repository?.getImages()?.body()!!)

                } catch (e: Exception){
                    Log.e("GetImages Exception", e.toString())
                }
            }
        }
    }

    fun getImage(): Observable<String>{
        return Observable.create { subscriber ->
            subscriber.onNext(imageUrl)
        }
    }
}