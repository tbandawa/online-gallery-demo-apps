package me.tbandawa.android.gallerydemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.runBlocking
import me.tbandawa.android.gallerydemo.data.api.GalleryApi
import me.tbandawa.android.gallerydemo.data.model.Gallery
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

        enqueueResponse(fileName = "galleries.json")
        val response = service.fetchGalleries()
        val request = mockWebServer.takeRequest()

        MatcherAssert.assertThat(request.path, CoreMatchers.`is`("/api/gallery"))

        MatcherAssert.assertThat(response, IsNull.notNullValue())

        MatcherAssert.assertThat(response.code(), CoreMatchers.`is`(200))

        MatcherAssert.assertThat(response.body()?.size, CoreMatchers.`is`(3))

    }

    @Test
    fun `test fetch all gallery`() = runBlocking {

        enqueueResponse(fileName = "gallery.json")
        val response = service.fetchGallery(1)
        val request = mockWebServer.takeRequest()

        MatcherAssert.assertThat(request.path, CoreMatchers.`is`("/api/gallery/1"))

        MatcherAssert.assertThat(response, IsNull.notNullValue())

        MatcherAssert.assertThat(response.code(), CoreMatchers.`is`(200))

        MatcherAssert.assertThat(response.body(), CoreMatchers.isA(Gallery::class.java))
        MatcherAssert.assertThat(response.body()?.id, CoreMatchers.`is`(53))
        MatcherAssert.assertThat(response.body()?.title, CoreMatchers.`is`("title 3"))
        MatcherAssert.assertThat(response.body()?.description, CoreMatchers.`is`("description 3"))
        MatcherAssert.assertThat(response.body()?.images?.size, CoreMatchers.`is`(3))
        MatcherAssert.assertThat(response.body()?.created, CoreMatchers.`is`("2022-03-01T19:07:51.661+0000"))

    }

    @Test
    fun `test error 404`() = runBlocking {

        enqueueResponse(fileName = "404.json", code = 404)
        val response = service.fetchGallery(99)
        val request = mockWebServer.takeRequest()

        MatcherAssert.assertThat(request.path, CoreMatchers.`is`("/api/gallery/99"))

        MatcherAssert.assertThat(response, IsNull.notNullValue())

        MatcherAssert.assertThat(response.code(), CoreMatchers.`is`(404))

        MatcherAssert.assertThat(response.body(), IsNull.nullValue())

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