package com.wjdaudtn.mission.recyclerView

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wjdaudtn.mission.databinding.ActivityViewHolderTwoBinding
import com.wjdaudtn.mission.databinding.ItemMainBinding
import com.wjdaudtn.mission.databinding.ItemSubBinding


class ViewHolderTwo : AppCompatActivity() {
    private lateinit var binding: ActivityViewHolderTwoBinding
    data class TextAndType(val text: String, val type: Int)
    lateinit var data: MutableList<TextAndType>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewHolderTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val random = (1..2)
                data.add(TextAndType(query.toString(), random.random()))
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }


    private fun initView() {
        data = mutableListOf(
            TextAndType("MainItem", 1),
            TextAndType("MainItem", 2)
        )

        binding.recyclerView.adapter = MyAdapter(data)
        binding.recyclerView.layoutManager = LinearLayoutManager(baseContext)

    }

    class MyAdapter(private var data: MutableList<TextAndType>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when (viewType) {
                1 -> MyViewHolder1(
                    ItemMainBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

                else -> MyViewHolder2(
                    ItemSubBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


            when (holder) {
                is MyViewHolder1 -> holder.binding.itemMainData.text = "$position ${data[position].text}"
                is MyViewHolder2 -> holder.binding.itemSubData.text = "$position ${data[position].text}"
            }
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun getItemViewType(position: Int): Int {
            return data[position].type
        }
    }
    class MyViewHolder1(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)
    class MyViewHolder2(val binding: ItemSubBinding) : RecyclerView.ViewHolder(binding.root)
}