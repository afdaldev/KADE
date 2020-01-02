package id.afdaldev.footballmatchscheduleapp.lookupleague

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import id.afdaldev.footballmatchscheduleapp.data.model.LookUpLeague
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LookUpLeagueViewModelTest {

    private val idLeague = "4328"

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var lookUpLeagueRepository: LookUpLeagueRepository

    @Mock
    private lateinit var lookUpLeagueViewModel: LookUpLeagueViewModel

    @Mock
    private lateinit var lookUpLeagueList: MutableLiveData<LookUpLeague>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        lookUpLeagueViewModel = LookUpLeagueViewModel(lookUpLeagueRepository)
    }

    @Test
    fun getLookUpLeagueList() {
        val observer = mock<Observer<LookUpLeague>>()
        val liveData = MutableLiveData<LookUpLeague>()

        `when`(lookUpLeagueViewModel.getLookUpLeagueList(idLeague))
            .thenReturn(lookUpLeagueList)

        liveData.observeForever(observer)
        liveData.value = lookUpLeagueViewModel.getLookUpLeagueList(idLeague).value

        argumentCaptor<LookUpLeague>().apply {
            verify(observer, times(1)).onChanged(capture())
        }
    }
}