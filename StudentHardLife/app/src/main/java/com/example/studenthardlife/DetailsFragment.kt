package com.example.studenthardlife

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.studenthardlife.databinding.UserInputBinding
import com.example.studenthardlife.databinding.FragmentTaskDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var taskId: String
    private lateinit var dbHandler: DBHandler
    private lateinit var task: Task
    private val binding by lazy {
        FragmentTaskDetailsBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { taskId = it.getString("taskId").toString() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context = this.context
        if (context != null) {
            dbHandler = DBHandler(context)

            task = dbHandler.getTasks().find { task -> task.id.toString() == taskId }!!
            binding.taskIDDetText.text = task.taskName
            binding.courseIDDetText.text = task.courseName
            binding.descriptionDetText.text = task.taskDescription

            binding.deleteButton.setOnClickListener {
                dbHandler.deleteTask(task)
                Navigation.findNavController(binding.root).navigate(R.id.action_taskDetailsFragment_to_taskOverviewFragment)
            }

            binding.editButton.setOnClickListener {
                setupDialog(task)
            }

            binding.backButton.setOnClickListener {
                Navigation.findNavController(binding.root).navigate(R.id.action_taskDetailsFragment_to_taskOverviewFragment)
            }
        }

        return binding.root
    }

    private fun setupDialog(item: Task) {
        val context = this.context
        if (context != null) {
            val dialog = Dialog(context)
            val dialogBinding = UserInputBinding.inflate(LayoutInflater.from(context))
            dialog.apply {
                setCancelable(false)
                setContentView(dialogBinding.root)
            }

            dialogBinding.apply {
                taskInput.setText(item.taskName)
                courseInput.setText(item.courseName)
                descInput.setText(item.taskDescription)
                updateButton.setOnClickListener {
                    updateDialog(dialogBinding, item, dialog)
                }

                cancelButton.setOnClickListener { dialog.dismiss() }
            }
            dialog.show()
        }
    }

    private fun updateDialog(dialogBinding: UserInputBinding, item: Task, dialog: Dialog) {
        val updateTaskID = dialogBinding.taskInput.text.toString()
        val updateCourseID = dialogBinding.courseInput.text.toString()
        val updateDescription = dialogBinding.descInput.text.toString()

        if (updateTaskID.isNotEmpty() && updateCourseID.isNotEmpty() && updateDescription.isNotEmpty()) {
            dbHandler.updateTask(item.id, updateTaskID, updateCourseID, updateDescription)
            task = dbHandler.getTasks().find { task -> task.id.toString() == taskId }!!
            binding.taskIDDetText.text = task.taskName
            binding.courseIDDetText.text = task.courseName
            binding.descriptionDetText.text = task.taskDescription
            dialog.dismiss()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}