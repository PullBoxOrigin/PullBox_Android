package com.antique_boss.news

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.antique_boss.news.databinding.FragmentNewsBinding


class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var hotPostAdapter: HotPostAdapter
    private val newsViewModel by navGraphViewModels<NewsViewModel>(R.id.news_nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        setupHotPostRecyclerView()
        setupObservers()

        //TODO need to fetch urls from firebase realtime database. & setupHotPostPreviews method should be called in the observer after getting the urls.
        setupHotPostPreviews(listOf(
            "https://0muwon.com/entry/%EC%9D%B4%EB%A0%A5%EC%84%9C-%EC%93%B0%EB%8A%94-%EB%B2%95-%EC%9E%91%EC%84%B1%EC%9A%94%EB%A0%B9-%EC%95%8C%EC%95%84%EB%B3%B4%EA%B8%B0",
            "https://career.woosuk.ac.kr/ko/dataroom/guide/view/1010",
            "https://brunch.co.kr/@comento/57",
            "https://oppapost.com/4748"
        ))
    }

    //TODO loading shimmer should be applied
    private fun setupHotPostRecyclerView() {
        hotPostAdapter = HotPostAdapter()
        binding.hotPostRecyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.hotPostRecyclerView.adapter = hotPostAdapter
    }

    private fun setupObservers() {
        newsViewModel.hotPostPreviews.observe(viewLifecycleOwner) {
            hotPostAdapter.submitList(it)
        }
    }

    private fun setupHotPostPreviews(urls: List<String>) {
        if(newsViewModel.hotPostPreviews.value.isNullOrEmpty())
        newsViewModel.fetchPreviews(urls)
    }

}