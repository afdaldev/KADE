package id.afdaldev.footballmatchscheduleapp.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import id.afdaldev.footballmatchscheduleapp.data.model.Search
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SearchViewModelTest {

    private val search = "city"

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private val searchViewModel = SearchViewModel()

    @Mock
    private lateinit var searchList: MutableLiveData<Search>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getSearchFromAPI() {
        val observer = mock<Observer<Search>>()
        val liveData = MutableLiveData<Search>()

        `when`(searchViewModel.getSearchFromAPI(search))
            .thenReturn(searchList)

        liveData.observeForever(observer)
        liveData.value = searchViewModel.getSearchFromAPI(search).value

        argumentCaptor<Search>().apply {
            verify(observer, times(1)).onChanged(capture())
        }
    }
}