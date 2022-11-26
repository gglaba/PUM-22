package com.example.studentcrimesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


private lateinit var adapter: RecyclerAdapter
private lateinit var recyclerView: RecyclerView
private lateinit var crimesArray: ArrayList<Crimes>


lateinit var crimeTitle : Array<String>
lateinit var crimeDescription : Array<String>
lateinit var studentIndex : Array<Int>
lateinit var isSolved : Array<Boolean>

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInit()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = RecyclerAdapter(crimesArray)
        recyclerView.adapter = adapter
    }

    private fun dataInit()
    {
        crimesArray = arrayListOf()

        crimeTitle = arrayOf(
            " Crime #1",
            " Crime #2",
            " Crime #3",
            " Crime #4",
            " Crime #5",
            " Crime #6",
            " Crime #7",
            " Crime #8",
            " Crime #9",
            " Crime #10",
            " Crime #11",
            " Crime #12",
            " Crime #13",
            " Crime #14",
            " Crime #15",
            " Crime #16",
            " Crime #17",
            " Crime #18",
            " Crime #19",
            " Crime #20",
            " Crime #21"
        )

        crimeDescription = arrayOf(

            "#1 Very bad crime",
            "#2 crime crime crime",
            "#3 Very bad bad bad crime",
            "#4 Very bad crime",
            "#5 Very bad bad crime",
            "#6 Very very crime",
            "#7 Very bad crime",
            "#8 Very bad crime",
            "#9 Very bad crime",
            "#10 Very bad crime",
            "#11 Very bad crime",
            "#12 Very bad crime",
            "#13 Very very bad crime",
            "#14 Very very very bad crime",
            "#15 Very very very bad crime",
            "#16 Very bad crime",
            "#17 crime bad crime",
            "#18 Very bad crime",
            "#19 Very bad crime",
            "#20 Very bad crime",
            "#21 Very bad crime",

            )

        studentIndex = arrayOf(
            315631,
            315632,
            315633,
            315634,
            315635,
            315636,
            315637,
            315638,
            315639,
            315610,
            315611,
            315612,
            315613,
            315614,
            315615,
            315616,
            315617,
            315618,
            315619,
            315620,
            315621,

            )

        isSolved = arrayOf(
            true,
            false,
            true,
            true,
            false,
            true,
            true,
            true,
            true,
            true,
            false,
            false,
            true,
            true,
            false,
            true,
            true,
            false,
            true,
            true,
            true,
        )

        for(i in crimeTitle.indices)
        {
            val c = Crimes(crimeTitle[i], studentIndex[i], crimeDescription[i],isSolved[i],i)
            crimesArray.add(c)
        }

    }

}

