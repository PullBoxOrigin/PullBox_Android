package com.antique_boss.portfolio.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.antique_boss.portfolio.adapter.CommentAdapter
import com.antique_boss.portfolio.R
import com.antique_boss.portfolio.databinding.FragmentCommentBottomSheetBinding
import com.antique_boss.portfolio.viewmodel.PortfolioViewModel
import com.antique_boss.portfolio.viewmodel.PortfolioViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CommentBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentCommentBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val portfolioViewModelFactory = PortfolioViewModelFactory()
    private val portfolioViewModel by navGraphViewModels<PortfolioViewModel>(R.id.portfolio_nav_graph) { portfolioViewModelFactory }
    private lateinit var commentAdapter: CommentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_comment_bottom_sheet, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        setupViewListener()
        setupCommentRecyclerView()
        setupObservers()
        setupViewState()
    }

    private fun setupViewListener() {
       binding.commentInputView.addTextChangedListener(object : TextWatcher {
           override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               portfolioViewModel.updateComment(s.toString())
           }
           override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
           override fun afterTextChanged(s: Editable?) {}
       })

        binding.commentSubmitButton.setOnClickListener {
            portfolioViewModel.attachComment()
            commentAdapter.notifyInserted()
        }
    }

    private fun setupCommentRecyclerView() {
        commentAdapter = CommentAdapter(setupPopupMenu())
        binding.commentRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.commentRecyclerView.adapter = commentAdapter
    }

    private fun setupPopupMenu(): (View, Int) -> Unit = { view, position ->
        val popupMenu = PopupMenu(requireActivity(), view)
        requireActivity().menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.menu_report -> {
                    commentAdapter.excludeComment(position)
                    true
                }
                R.id.menu_block -> {
                    commentAdapter.excludeComment(position)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    private fun setupObservers() {
        portfolioViewModel.comments.observe(viewLifecycleOwner) {
            commentAdapter.submitList(it)
        }

        portfolioViewModel.comment.observe(viewLifecycleOwner) {
            portfolioViewModel.validateRequiredElement()
        }

        portfolioViewModel.submit.observe(viewLifecycleOwner) {
            when(it) {
                true -> {
                    binding.commentSubmitButton.isEnabled = true
                }
                false -> {
                    binding.commentSubmitButton.isEnabled = false
                }
            }
        }
    }

    private fun setupViewState() {
        portfolioViewModel.loadComments()
    }
}