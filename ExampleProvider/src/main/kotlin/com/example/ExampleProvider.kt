package com.example

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.lagradost.cloudstream3.utils.ExtractorLinkType
import com.lagradost.cloudstream3.utils.Qualities
import com.lagradost.cloudstream3.utils.newExtractorLink
class ExampleProvider : MainAPI() {
    override var mainUrl = "https://example.com"
    override var name = "Example Provider"
    override var lang = "en"
    override val supportedTypes = setOf(TvType.Movie)
    override var hasMainPage = false

    // Search implementation
    override suspend fun search(query: String): List<SearchResponse> {
        if (query.equals("test", ignoreCase = true)) {
            return listOf(
                newMovieSearchResponse(
                    name = "CrossPlatform Test Movie",
                    url = "$mainUrl/test-movie"
                ) {
                    type = TvType.Movie
                posterUrl = "https://peach.blender.org/wp-content/uploads/poster_bunny_big.jpg"
                }
            )
        }
        return emptyList()
    }

    // Load movie details
    override suspend fun load(url: String): LoadResponse {
        return newMovieLoadResponse(
            name = "CrossPlatform Test Movie",
            url = url,
            dataUrl = url,
            type = TvType.Movie
        ) {
            //posterUrl = "$mainUrl/images/test-movie.jpg"
            posterUrl = "https://peach.blender.org/wp-content/uploads/poster_bunny_big.jpg"
            year = 2025
            plot = "This is a dummy movie provided by  Blender Foundation | www.blender.org for testing."
        }
    }

    // Resolve streaming links
    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        callback(
            newExtractorLink(
                source = name,
                name = "Test Stream",
                url = "https://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_320x180.mp4"
                //type = ExtractorLinkType.
            ) {
                // set extra fields inside the initializer block
                referer = mainUrl
                quality = Qualities.P1080.value
            }
        )
        return true
    }
}
