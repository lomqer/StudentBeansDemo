package com.dziaber.studentbeansdemo.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dziaber.studentbeansdemo.R
import com.dziaber.studentbeansdemo.databinding.FragmentPhotosBinding

class PhotosFragment : Fragment() {

    private lateinit var binding: FragmentPhotosBinding
    private lateinit var photosViewModel: PhotosViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentPhotosBinding.inflate(layoutInflater)

        val recyclerView = binding.photoRecyclerView
        val backButton = binding.backButton

        backButton.setOnClickListener{
            this.findNavController().navigate(R.id.action_photosFragment_to_loginFragment)
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        photosViewModel = ViewModelProvider(this, PhotosViewModelFactory())[PhotosViewModel::class.java]
        photosViewModel.photos.observe(viewLifecycleOwner) {
            recyclerView.adapter = PhotoListAdapter(it)
        }
        photosViewModel.updatePhotos()
        return binding.root
    }
}