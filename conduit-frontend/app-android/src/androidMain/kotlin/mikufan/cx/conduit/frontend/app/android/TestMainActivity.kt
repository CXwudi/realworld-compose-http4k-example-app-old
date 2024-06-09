package mikufan.cx.conduit.frontend.app.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import mikufan.cx.conduit.frontend.ui.TestMainUI

class TestMainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      TestMainUI()
    }
  }
}