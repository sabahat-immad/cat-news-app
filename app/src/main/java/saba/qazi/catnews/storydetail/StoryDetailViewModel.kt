package saba.qazi.catnews.storydetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import saba.qazi.catnews.storydetail.data.StoryDetail

class StoryDetailViewModel(
    private val service: StoryDetailService
) : ViewModel(){

    val storyDetails : MutableLiveData<Result<StoryDetail>> = MutableLiveData()

    fun getStoryDetails(id: String) {
        viewModelScope.launch {
            service.fetchStoryDetail(id)
                .collect {
                    storyDetails.postValue(it)
                }
        }

    }

}
