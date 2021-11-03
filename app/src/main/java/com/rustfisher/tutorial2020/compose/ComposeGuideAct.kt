package com.rustfisher.tutorial2020.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

/**
 * @author rustfisher
 * @date 2021-11-03 09:32
 */
class ComposeGuideAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuidePage()
        }
    }
}

@Composable
private fun GuidePage() {
    Column {
        Text(text = "Compose 示例", color = Color.Yellow)
        Text(text = "an.rustfisher.com", color = Color.Yellow)
        ShowHello(name = "小明")
        ShowHello(name = "小强")
    }
}

@Composable
private fun ShowHello(name: String) {
    Text(text = "Hi $name", color = Color.Cyan)
}

@Preview("guide")
@Preview("guide - big", fontScale = 1.2f)
@Composable
fun PreviewPost() {
    GuidePage()
}
