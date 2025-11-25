package com.example

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.SearchResponse
import com.lagradost.cloudstream3.MovieSearchResponse
import com.lagradost.cloudstream3.TvType

class ExampleProvider : MainAPI() {
    override var mainUrl = "https://example.com/"
    override var name = "Example Provider"
    override var lang = "en"
    override val supportedTypes = setOf(TvType.Movie)
    override val hasMainPage = false

    override suspend fun search(query: String): List<SearchResponse> {
        // Simple test: if user searches "test", return a dummy movie
        if (query.equals("test", ignoreCase = true)) {
            return listOf(
                MovieSearchResponse(
                    name = "CrossPlatform Test Movie",
                    url = "$mainUrl/test-movie",
                    apiName = name,
                    type = TvType.Movie
                )
            )
        }
        // Otherwise return nothing until you implement real scraping
        return emptyList()
    }
}
