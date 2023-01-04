package com.example.studenthardlife


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studenthardlife.databinding.FragmentTaskOverviewBinding

class OverviewFragment : Fragment() {

    private lateinit var dbHandler: DBHandler
    private val binding by lazy {
        FragmentTaskOverviewBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context = this.context
        if (context != null) {
            dbHandler = DBHandler(context)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = Adapter(dbHandler)
        }

        binding.addButton.setOnClickListener {
            val taskName = binding.taskNameEditText.text.toString()
            val courseName = binding.courseNameEditText.text.toString()
            val taskDescription = binding.taskDescriptionEditText.text.toString()

            if (taskName.isNotEmpty() && courseName.isNotEmpty() && taskDescription.isNotEmpty())
            {
                dbHandler.addTask(Task(taskName, courseName, taskDescription))
                binding.taskNameEditText.text.clear()
                binding.courseNameEditText.text.clear()
                binding.taskDescriptionEditText.text.clear()
            }

            binding.recyclerView.adapter?.notifyItemInserted(dbHandler.getTasks().size)
        }

        return binding.root
    }

    override fun onDestroy() {
        dbHandler.close()
        super.onDestroy()
    }

}