package com.example.homeworkretrofit.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworkretrofit.model.ProductModel
import com.example.homeworkretrofit.model.Result
import com.example.homeworkretrofit.network.RetrfitObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyFragmentViewModel : ViewModel() {
    private val service = RetrfitObject.createCustomService()
    private val _productData = MutableLiveData<Result<List<ProductModel>>>()
    val productData: LiveData<Result<List<ProductModel>>>
        get() = _productData

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.getProductList()

                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        val productList = data.products // Replace 'products' with the actual field name in your ResponseModel
                        _productData.postValue(Result.Success(productList ?: emptyList()))
                    } else {
                        _productData.postValue(Result.Failure("Response body is null", null))
                    }
                } else {
                    _productData.postValue(Result.Failure(response.message(), null))
                }
            } catch (e: Exception) {
                _productData.postValue(Result.Failure(e.message, e))
            }
        }
    }
}
