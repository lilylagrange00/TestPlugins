package com.example

import com.lagradost.cloudstream3.MainAPI
import com.lagradost.cloudstream3.SearchResponse
import com.lagradost.cloudstream3.TvType
import com.lagradost.cloudstream3.newMovieSearchResponse

class ExampleProvider : MainAPI() {
    override var mainUrl = "https://example.com/"
    override var name = "Example Provider"
    override val supportedTypes = setOf(TvType.Movie)
    override val hasMainPage = false

    override suspend fun search(query: String): List<SearchResponse> {
        if (query.equals("test", ignoreCase = true)) {
            return listOf(
                newMovieSearchResponse(
                    name = "CrossPlatform Test Movie",
                    url = "$mainUrl/test-movie"
                ) {
                    this.apiName = name
                    this.type = TvType.Movie
                }
            )
        }
        return emptyList()
    }
}
