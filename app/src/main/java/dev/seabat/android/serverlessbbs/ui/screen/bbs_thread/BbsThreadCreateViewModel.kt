package dev.seabat.android.serverlessbbs.ui.screen.bbs_thread

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.seabat.android.serverlessbbs.constants.BbsThreadColor

class BbsThreadCreateViewModel : ViewModel() {
    private var _bbsThreadColor= MutableLiveData<Int>()
    val bbsThreadColor: LiveData<Int>
        get() = _bbsThreadColor

    fun changeBackgroundColor(color: String) {
        _bbsThreadColor.value = BbsThreadColor.covertToId(color)
    }
}