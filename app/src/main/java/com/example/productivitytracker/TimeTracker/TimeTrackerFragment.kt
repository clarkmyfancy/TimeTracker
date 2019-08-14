package com.example.productivitytracker.TimeTracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.productivitytracker.R
import com.example.productivitytracker.databinding.FragmentTimeTrackerBinding

class TimeTrackerFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)

        val binding: FragmentTimeTrackerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_time_tracker, container, false)

        return binding.root
    }
}