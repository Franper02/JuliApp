package com.example.juli.overview


import androidx.lifecycle.*
import com.example.juli.network.Foto
import com.example.juli.network.FotoApi
import kotlinx.coroutines.*
import java.lang.Exception


class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()
    private val _photos = MutableLiveData<List<Foto>>()
    var _url = MutableLiveData<String>()

    val status : LiveData<String> = _status
    val photos : LiveData<List<Foto>> = _photos
    val url : LiveData<String> = _url



    fun getFoto(urll : String){
        val actualUrl = urll.split("/")
        viewModelScope.launch{
            try{
                _photos.postValue(FotoApi.retrofitService.getPhotos("${actualUrl[3] + "/" + actualUrl[4]}"))
                _status.value = "Success: Fotos recibidas"
            }catch (e: Exception){
                _status.postValue("Failure: ${e.message}")
            }
        }
    }


}