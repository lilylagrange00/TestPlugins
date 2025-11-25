package com.example

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.ExtractorLink
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
            posterUrl = "$mainUrl/images/test-movie.jpg"
            year = 2025
            plot = "This is a dummy movie provided by ExampleProvider for testing."
        }
    }

    // Resolve streaming links
    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        // Dummy link for testing
        callback(
            newExtractorLink(
                source = name,
                name = "Test Stream",
                url = "https://example.com/stream/test.mp4",
                referer = mainUrl,
                quality = Qualities.P1080,
                isM3u8 = false
            )
        )
        return true
    }
}
