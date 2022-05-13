package com.atahar.flickrimage.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.atahar.flickrimage.App
import com.atahar.flickrimage.R
import com.atahar.flickrimage.databinding.FragmentSearchBinding
import com.atahar.flickrimage.utils.Constants
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var currentSearchQuery: String = ""

    @Inject
    lateinit var searchViewModel: SearchViewModel

/*
    private val searchViewModel: SearchViewModel by viewModels {
        SearchViewModel.SearchViewModelFactory(
            ((requireActivity().application) as App).getPhotosUseCase
        )
    }
*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentSearchQuery = requireArguments().getString("query") ?: ""
        searchViewModel.resetData()
        searchViewModel.getPhotos(currentSearchQuery)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentSearchBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = searchViewModel

        val adapter = PhotoAdapter()
        binding.photosRecyclerView.adapter = adapter

        searchViewModel.status.observe(viewLifecycleOwner) { loadingStatus ->
            if (loadingStatus == LoadingStatus.LOADING) {
                binding.loadMoreProgress.visibility = View.VISIBLE
            } else if (loadingStatus == LoadingStatus.DONE || loadingStatus == LoadingStatus.ERROR) {
                binding.loadMoreProgress.visibility = View.GONE
            } else {
                binding.loadMoreProgress.visibility = View.GONE
                showErrorMessage(binding.root, getString(R.string.error_no_data))
                Handler(Looper.getMainLooper()).postDelayed(
                    { activity?.finish() },
                    Constants.DELAY_NO_DATA
                )
            }
        }

        searchViewModel.photos.observe(viewLifecycleOwner) {
            adapter.notifyDataSetChanged()
        }

        binding.photosRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!binding.photosRecyclerView.canScrollVertically(1)) {
                    if (searchViewModel.currentPage < searchViewModel.totalPage && currentSearchQuery.isNotEmpty()) {
                        searchViewModel.getPhotos(currentSearchQuery)
                    }
                }
            }
        })

        return binding.root
    }

    private fun showErrorMessage(view: View, msg: String) {
        val snackBar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
        val snackBarView: View = snackBar.view
        snackBarView.setBackgroundColor(ContextCompat.getColor(view.context, R.color.snackbar_bg))
        snackBar.show()
    }

}