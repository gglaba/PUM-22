package com.example.mymusiclibrary

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class DetailFragmentDirections private constructor() {
  public companion object {
    public fun actionDetailFragmentToListFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_detailFragment_to_listFragment)
  }
}
