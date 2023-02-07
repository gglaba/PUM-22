package com.example.mymusiclibrary

import android.os.Bundle
import androidx.navigation.NavDirections
import kotlin.Int

public class ListFragmentDirections private constructor() {
  private data class ActionListFragmentToDetailFragment(
    public val currentExerciseIndex: Int
  ) : NavDirections {
    public override val actionId: Int = R.id.action_listFragment_to_detailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("currentExerciseIndex", this.currentExerciseIndex)
        return result
      }
  }

  public companion object {
    public fun actionListFragmentToDetailFragment(currentExerciseIndex: Int): NavDirections =
        ActionListFragmentToDetailFragment(currentExerciseIndex)
  }
}
