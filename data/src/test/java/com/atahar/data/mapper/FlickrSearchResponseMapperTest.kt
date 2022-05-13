package com.atahar.data.mapper

import com.atahar.data.api.FlickrPhoto
import com.atahar.data.api.FlickrPhotos
import com.atahar.data.api.FlickrSearchResponse
import org.junit.Assert
import org.junit.Test

class FlickrSearchResponseMapperTest {

    private val mockFlickrPhotoJson = FlickrPhoto(
        "id",
        "owner",
        "secret",
        "server",
        "farm",
        "title",
        "ispublic",
        "isfriend",
        "isfamily"
    )

    private val mockFlickrPhotosJson = FlickrPhotos(
        1,
        20,
        "5",
        "100",
        listOf(mockFlickrPhotoJson)
    )

    private val mockSearchResponseJson = FlickrSearchResponse(mockFlickrPhotosJson)

    @Test
    fun `convert search response to data class`() {
        val mapper = FlickrSearchResponseMapper()
        val flkrPhotos = mapper.toFlkrPhotos(mockSearchResponseJson)

        Assert.assertEquals(flkrPhotos.page, 1)
        Assert.assertEquals(flkrPhotos.pages, 20)
        Assert.assertEquals(flkrPhotos.perpage, "5")
        Assert.assertEquals(flkrPhotos.total, "100")
        Assert.assertTrue(flkrPhotos.photo.isNotEmpty())

        Assert.assertEquals(flkrPhotos.photo[0].id, "id")
        Assert.assertEquals(flkrPhotos.photo[0].owner, "owner")
        Assert.assertEquals(flkrPhotos.photo[0].secret, "secret")
        Assert.assertEquals(flkrPhotos.photo[0].server, "server")
        Assert.assertEquals(flkrPhotos.photo[0].farm, "farm")
        Assert.assertEquals(flkrPhotos.photo[0].title, "title")
    }

}