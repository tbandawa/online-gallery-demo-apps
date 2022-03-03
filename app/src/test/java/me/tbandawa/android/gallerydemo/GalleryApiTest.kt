package me.tbandawa.android.gallerydemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.runBlocking
import me.tbandawa.android.gallerydemo.data.api.GalleryApi
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class GalleryApiTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: GalleryApi

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createServiceAndRepository() {

        mockWebServer = MockWebServer()

        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GalleryApi::class.java)

    }

    @Test
    fun `test fetch all galleries`() = runBlocking {

        enqueueResponse("galleries.json")
        val response = service.fetchGalleries()
        val request = mockWebServer.takeRequest()

        MatcherAssert.assertThat(request.path, CoreMatchers.`is`("api/gallery"))

        MatcherAssert.assertThat(response, IsNull.notNullValue())

        MatcherAssert.assertThat(response.code(), CoreMatchers.`is`(200))

        MatcherAssert.assertThat(response.body()?.size, CoreMatchers.`is`(3))

    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    private fun enqueueResponse(
        fileName: String,
        code: Int = 200,
        headers: Map<String, String> = emptyMap()
    ) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
            .setResponseCode(code)
        for ((key, value) in headers)
            mockResponse.addHeader(key, value)
        mockWebServer.enqueue(mockResponse.setBody(source.readString(Charsets.UTF_8)))
    }

}