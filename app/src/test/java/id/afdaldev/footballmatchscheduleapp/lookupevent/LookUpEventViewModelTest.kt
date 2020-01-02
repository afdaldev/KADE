package id.afdaldev.footballmatchscheduleapp.lookupevent

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import id.afdaldev.footballmatchscheduleapp.data.model.Event
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LookUpEventViewModelTest {

    private val idEvent = "671590"

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var lookUpEventRepository: LookUpEventRepository

    @Mock
    private lateinit var lookUpEventViewModel: LookUpEventViewModel

    @Mock
    private lateinit var lookUpEventList: MutableLiveData<Event>


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        lookUpEventViewModel = LookUpEventViewModel(lookUpEventRepository)
    }

    @Test
    fun getLookUpEvent() {
        val observer = mock<Observer<Event>>()
        val liveData = MutableLiveData<Event>()

        `when`(lookUpEventViewModel.getLookUpEvent(idEvent))
            .thenReturn(lookUpEventList)

        liveData.observeForever(observer)
        liveData.value = lookUpEventViewModel.getLookUpEvent(idEvent).value

        argumentCaptor<Event>().apply {
            verify(observer, times(1)).onChanged(capture())
        }
    }
}