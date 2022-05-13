package com.atahar.flickrimage.utils

import android.content.SearchRecentSuggestionsProvider

class FlickrSuggestionProvider : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        const val AUTHORITY = "com.atahar.flickrimage.utils.MySuggestionProvider"
        const val MODE: Int = DATABASE_MODE_QUERIES
    }
}
