package com.tapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapp.data.request.LoginRequest
import com.tapp.data.response.UserResponse
import com.tapp.di.InternetClient
import com.tapp.domain.User
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val token = MutableLiveData<String>()
    val error = MutableLiveData<String>()

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            val loginRequest = LoginRequest(userName, password)
            val internetClient = InternetClient
            val mainApi = internetClient.createMainApi()
            try {
                val loginResponse: UserResponse = mainApi.login(loginRequest)
                val user = User(firstName = loginResponse.firstName, image = loginResponse.image)
            } catch (e: Exception) {
                error.value = "Ошибка авторизации"
            }
        }


        /*    CoroutineScope(Dispatchers.IO).launch {
                val response = InternetClient.createMainApi().login(loginRequest)
                val message = response.errorBody()?.string()?.let {
                    JSONObject(it).getString("message")
                }
                withContext(Dispatchers.Main) {
                    binding.error.text = message
                    val user = response.body()?.toDomain()
                    if (user != null) {
                        Picasso.get().load(user.image).into(binding.userImage)
                        binding.userName.text = user.firstName
                        binding.next.visibility = View.VISIBLE
                        viewModel.token.value = user.token
                    }
                }
            }*/
    }
}