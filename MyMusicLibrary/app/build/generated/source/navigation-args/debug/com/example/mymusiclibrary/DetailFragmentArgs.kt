package com.example.mymusiclibrary

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.jvm.JvmStatic

public data class DetailFragmentArgs(
  public val currentExerciseIndex: Int
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("currentExerciseIndex", this.currentExerciseIndex)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("currentExerciseIndex", this.currentExerciseIndex)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): DetailFragmentArgs {
      bundle.setClassLoader(DetailFragmentArgs::class.java.classLoader)
      val __currentExerciseIndex : Int
      if (bundle.containsKey("currentExerciseIndex")) {
        __currentExerciseIndex = bundle.getInt("currentExerciseIndex")
      } else {
        throw IllegalArgumentException("Required argument \"currentExerciseIndex\" is missing and does not have an android:defaultValue")
      }
      return DetailFragmentArgs(__currentExerciseIndex)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): DetailFragmentArgs {
      val __currentExerciseIndex : Int?
      if (savedStateHandle.contains("currentExerciseIndex")) {
        __currentExerciseIndex = savedStateHandle["currentExerciseIndex"]
        if (__currentExerciseIndex == null) {
          throw IllegalArgumentException("Argument \"currentExerciseIndex\" of type integer does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"currentExerciseIndex\" is missing and does not have an android:defaultValue")
      }
      return DetailFragmentArgs(__currentExerciseIndex)
    }
  }
}
