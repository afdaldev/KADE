package id.afdaldev.footballmatchscheduleapp.event

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

class EventViewModelTest {

    private val idLeague = "4328"

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var eventRepository: EventRepository

    private lateinit var eventViewModel: EventViewModel

    @Mock
    private lateinit var eventList: MutableLiveData<Event>

    @Before
    fun setUp()  {
        MockitoAnnotations.initMocks(this)
        eventViewModel = EventViewModel(eventRepository)
    }

    @Test
    fun getPastEvent() {
        val observer = mock<Observer<Event>>()
        val liveData = MutableLiveData<Event>()
        `when`(eventViewModel.getPastEvent(idLeague))
            .thenReturn(eventList)

        liveData.observeForever(observer)
        liveData.value = eventViewModel.getPastEvent(idLeague).value

        argumentCaptor<Event>().apply {
            verify(observer, times(1)).onChanged(capture())
        }

    }

    @Test
    fun getNextEvent() {
        val observer = mock<Observer<Event>>()
        val liveData = MutableLiveData<Event>()

        `when`(eventViewModel.getNextEvent(idLeague))
            .thenReturn(eventList)

        liveData.observeForever(observer)
        liveData.value = eventViewModel.getNextEvent(idLeague).value

        argumentCaptor<Event>().apply {
            verify(observer, times(1)).onChanged(capture())
        }
    }
}