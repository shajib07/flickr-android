package com.atahar.flickrimage.presentation

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.atahar.flickrimage.R
import com.atahar.flickrimage.utils.FlickrSuggestionProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->

                title = query
                openSearchFragment(query)
                SearchRecentSuggestions(
                    this,
                    FlickrSuggestionProvider.AUTHORITY,
                    FlickrSuggestionProvider.MODE
                ).saveRecentQuery(query, null)

            }
        }
    }

    private fun openSearchFragment(query: String) {
        val bundle = bundleOf("query" to query)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<SearchFragment>(R.id.fragment_container_view, args = bundle)
        }
    }
}