package com.example.nasaimages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class NasaImageFragment : Fragment() {

    var navController: NavController? = null
    private val viewModel: NasaImageViewModel by activityViewModels()
    lateinit var recyclerView: RecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        swipeRefreshLayout = view.findViewById(R.id.nasa_image_swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getNasaData()
            swipeRefreshLayout.isRefreshing = false
        }
        viewModel.getNasaData()

        viewModel.nasaImageResponse.observe(viewLifecycleOwner, Observer {
            it?.let {
                recyclerView.adapter = NasaImagesAdapter(it)
            }
        })
        recyclerView = view.findViewById(R.id.nasaImage_recyclerView)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = linearLayoutManager
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nasa_image_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter = NasaImagesAdapter(viewModel.nasaImageResponse.value)
    }
}