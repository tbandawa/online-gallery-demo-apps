package me.tbandawa.android.gallerydemo

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.runBlocking
import me.tbandawa.android.gallerydemo.data.api.GalleryApi
import me.tbandawa.android.gallerydemo.data.model.ErrorResponse
import me.tbandawa.android.gallerydemo.data.model.Gallery
import me.tbandawa.android.gallerydemo.data.model.NetworkResult
import me.tbandawa.android.gallerydemo.data.repository.GalleryRepository
import me.tbandawa.android.gallerydemo.data.repository.GalleryRepositoryImpl
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class GalleryRepositoryTest {

    @Mock
    lateinit var galleryApi: GalleryApi

    private lateinit var galleryRepository: GalleryRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        galleryRepository = GalleryRepositoryImpl(galleryApi)
    }

    @Test
    fun `test fetch galleries`() = runBlocking {

        val galleriesResponse = readJsonResponse<List<Gallery>>("galleries.json")

        Mockito.`when`(galleryApi.fetchGalleries())
            .thenReturn(Response.success(galleriesResponse))

        galleryRepository.fetchGalleries().collect { value: NetworkResult<List<Gallery>> ->

            if(value is NetworkResult.Success) {
                MatcherAssert.assertThat(
                    value.data.size,
                    CoreMatchers.`is`(3)
                )
            }

        }

    }

    @Test
    fun `test fetch gallery`() = runBlocking {

        val galleryResponse = readJsonResponse<Gallery>("gallery.json")

        Mockito.`when`(galleryApi.fetchGallery(anyLong()))
            .thenReturn(Response.success(galleryResponse))

        galleryRepository.fetchGallery(1L).collect { value: NetworkResult<Gallery> ->

            if(value is NetworkResult.Success) {
                MatcherAssert.assertThat(
                    value.data.id,
                    CoreMatchers.`is`(53)
                )
            }

        }

    }

    @Test
    fun `test error 404`() = runBlocking {

        val errorResponse = readJsonResponse<ErrorResponse>("404.json")

        var gson = Gson()
        var jsonString = gson.toJson(errorResponse)

        val responseBody: ResponseBody = jsonString
            .toResponseBody("application/json".toMediaTypeOrNull())

        Mockito.`when`(galleryApi.fetchGallery(anyLong()))
            .thenReturn(Response.error(404, responseBody))

        galleryRepository.fetchGallery(1L).collect { value: NetworkResult<Gallery> ->

            if(value is NetworkResult.Error) {
                MatcherAssert.assertThat(value.data?.status, CoreMatchers.`is`(404))
                MatcherAssert.assertThat(value.data?.error, CoreMatchers.`is`("Not Found"))
            }

        }

    }

    private inline fun <reified T : Any> readJsonResponse(fileName: String) : T {
        val fileContent = this::class.java.classLoader.getResource(fileName).readText()
        return Gson().fromJson(fileContent, object : TypeToken<T>() {}.type)
    }



}