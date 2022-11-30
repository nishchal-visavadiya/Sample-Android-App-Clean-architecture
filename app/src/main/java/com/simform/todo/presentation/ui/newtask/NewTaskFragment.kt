package com.simform.todo.presentation.ui.newtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.simform.todo.databinding.FragmentNewTaskBinding
import com.simform.todo.presentation.ui.alert.Alerter
import com.simform.todo.presentation.utils.closeSoftInputKeyboard
import com.simform.todo.presentation.utils.observeEvent
import com.simform.todo.presentation.utils.obtainViewModel

class NewTaskFragment : Fragment() {

    private lateinit var mBinding: FragmentNewTaskBinding
    private val mAlerter by lazy { Alerter() }
    private val mNewTaskViewModel by lazy { requireActivity().obtainViewModel(NewTaskViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentNewTaskBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNewTaskViewModel.setup()
        setupUi()
    }

    private fun setupUi() {
        mBinding.btnCreate.setOnClickListener {
            val titleEditText = mBinding.edTextTitle
            val descriptionEditText = mBinding.edTextDescription
            mNewTaskViewModel.addTask(
                titleEditText.text.toString(),
                descriptionEditText.text.toString()
            ) {
                mAlerter.showSuccess("Created", it, requireActivity())
                titleEditText.text.clear()
                descriptionEditText.text.clear()
            }
            requireActivity().closeSoftInputKeyboard()
            titleEditText.clearFocus()
            descriptionEditText.clearFocus()
        }
    }

    private fun NewTaskViewModel.setup() {
        emptyError.observeEvent(viewLifecycleOwner) {
            mAlerter.showError("Empty", it.toString(), requireActivity())
        }
    }
}