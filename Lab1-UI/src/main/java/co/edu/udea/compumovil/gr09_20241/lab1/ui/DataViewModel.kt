package co.edu.udea.compumovil.gr09_20241.lab1.ui

import androidx.lifecycle.ViewModel
import co.edu.udea.compumovil.gr09_20241.lab1.data.FormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DataViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(FormUiState())
    val uiState: StateFlow<FormUiState> = _uiState.asStateFlow()

    fun setName(inputName: String){
        _uiState.update { currentState ->
            currentState.copy(name = inputName)
        }
    }

    fun setLastName(inputLastName: String){
        _uiState.update { currentState ->
            currentState.copy(lastName = inputLastName)
        }
    }

    fun setGender(inputGender: String){
        _uiState.update { currentState ->
            currentState.copy(gender = inputGender)
        }
    }

    fun setBirth(inputBirth: String){
        _uiState.update { currentState ->
            currentState.copy(birth = inputBirth)
        }
    }

    fun setScholarity(inputScholarity: String){
        _uiState.update { currentState ->
            currentState.copy(scholarity = inputScholarity)
        }
    }

    fun setPhoneNumber(inputPhoneNumber: Int){
        _uiState.update { currentState ->
            currentState.copy(phoneNumber = inputPhoneNumber)
        }
    }

    fun setAddress(inputAddress: String){
        _uiState.update { currentState ->
            currentState.copy(address = inputAddress)
        }
    }

    fun setEmail(inputEmail: String){
        _uiState.update { currentState ->
            currentState.copy(email = inputEmail)
        }
    }

    fun setCountry(inputCountry: String){
        _uiState.update { currentState ->
            currentState.copy(country = inputCountry)
        }
    }

    fun setCity(inputCity: String){
        _uiState.update { currentState ->
            currentState.copy(city = inputCity)
        }
    }
}