// Copyright (C) 2025 Zac Sweers
// SPDX-License-Identifier: Apache-2.0
package dev.zacsweers.metro.sample.circuit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.presenter.Presenter
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Inject
import io.ktor.client.HttpClient

@CircuitInject(CounterScreen::class, AppScope::class)
@Inject
class CounterPresenter(private val httpClient: HttpClient) : Presenter<CounterState> {
  @Composable
  override fun present(): CounterState {
    var count by remember { mutableStateOf(0) }

    return CounterState(count = count) { event ->
      when (event) {
        CounterEvent.Increment -> count++
        CounterEvent.Decrement -> count--
        CounterEvent.Reset -> count = 0
      }
    }
  }
}
