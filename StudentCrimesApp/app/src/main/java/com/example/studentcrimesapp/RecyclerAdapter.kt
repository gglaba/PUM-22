package com.example.studentcrimesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class RecyclerAdapter(private val crimesList : ArrayList<Crimes>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = crimesList[position]
        holder.crimeTitle.text = "\n ${currentItem.title}"
        if(currentItem.isSolved)
        {
            holder.handcuffImage.visibility = View.VISIBLE
        }
        else
        {
            holder.handcuffImage.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int {
        return crimesList.size
    }


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {

        val handcuffImage : ImageView = itemView.findViewById(R.id.handcuffsImage)
        val crimeTitle : TextView = itemView.findViewById(R.id.crimeTitle)

        init {
            itemView.setOnClickListener{
                val data = Bundle()
                data.putString("desc",crimesList[adapterPosition].description)
                data.putString("title",crimesList[adapterPosition].title)
                data.putInt("index", crimesList[adapterPosition].index_no)
                data.putBoolean("isSolved",crimesList[adapterPosition].isSolved)

                Navigation.findNavController(itemView).navigate(R.id.action_mainFragment_to_detailsFragment,data)

            }


        }
    }
}