package com.example.simpleapp.viewmodel

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleapp.R
import com.example.simpleapp.model.api.CatApi
import com.example.simpleapp.model.entity.Cat
import com.example.simpleapp.model.entity.CatResponse
import com.example.simpleapp.model.entity.catMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatViewModel(
    private val client: CatApi
) : ViewModel() {

    private val _catListUI = MutableLiveData<CatListUI>()
    val catListUI: LiveData<CatListUI> = _catListUI

    fun fetchCatList() {
        _catListUI.value = CatListUI.Loading(true)

        client.fetchCatListFromTMDB("british").enqueue(object : Callback<List<CatResponse>> {
            override fun onResponse(p0: Call<List<CatResponse>>, p1: Response<List<CatResponse>>) {
                if (!p1.isSuccessful) {
                    _catListUI.value = CatListUI.Error(R.string.error_general)
                    return
                }

                val catList = p1.body()

                Log.d("fetchCatList", catList.toString())

                if (catList != null) {
                    _catListUI.value = CatListUI.Success(catList.map(catMapper))
                    _catListUI.value = CatListUI.Loading(false)
                } else {
                    _catListUI.value = CatListUI.Empty
                }
            }

            override fun onFailure(p0: Call<List<CatResponse>>, p1: Throwable) {
                Log.e("fetchCatList", "Request failed: ${p1.message}", p1)
                _catListUI.value = CatListUI.Error(R.string.error_general)
            }
        })
    }

    fun searchCats(query: String) {
        if(query == ""){
            return
        }

        _catListUI.value = CatListUI.Loading(true)

        client.fetchCatListFromTMDB(query).enqueue(object : Callback<List<CatResponse>> {
            override fun onResponse(call: Call<List<CatResponse>>, response: Response<List<CatResponse>>) {
                if (!response.isSuccessful) {
                    _catListUI.value = CatListUI.Error(R.string.error_general)
                    return
                }

                val catList = response.body()

                Log.d("searchCats", "Search result: $catList")

                if (catList != null) {
                    _catListUI.value = CatListUI.Success(catList.map(catMapper))
                } else {
                    _catListUI.value = CatListUI.Empty
                }
                _catListUI.value = CatListUI.Loading(false)
            }

            override fun onFailure(call: Call<List<CatResponse>>, t: Throwable) {
                Log.e("searchCats", "Request failed: ${t.message}", t)
                _catListUI.value = CatListUI.Error(R.string.error_general)
                _catListUI.value = CatListUI.Loading(false)
            }
        })
    }
}

sealed interface CatListUI {
    data class Loading(val isLoading: Boolean) : CatListUI
    data class Error(@StringRes val errorMessage: Int) : CatListUI
    data class Success(val catList: List<Cat>) : CatListUI
    data object Empty : CatListUI
}
