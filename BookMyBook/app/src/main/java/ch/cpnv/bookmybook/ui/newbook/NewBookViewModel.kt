package ch.cpnv.bookmybook.ui.newbook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewBookViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "New Book"
    }
    val text: LiveData<String> = _text
}