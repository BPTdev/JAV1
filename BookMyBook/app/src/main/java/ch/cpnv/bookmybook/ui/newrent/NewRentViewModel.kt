package ch.cpnv.bookmybook.ui.newrent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewRentViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "New Rent"
    }
    val text: LiveData<String> = _text
}