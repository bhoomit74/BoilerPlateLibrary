package com.bhoomit.boilerplate

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bhoomit.boilerplatelibrary.Link
import com.bhoomit.boilerplatelibrary.MakeLinks.makeLinks
import com.bhoomit.boilerplatelibrary.showToast
import com.bhoomit.boilerplatelibrary.snackBar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val image = findViewById<ImageView>(R.id.image)
        val textview = findViewById<TextView>(R.id.textview)

        image.snackBar("SnackBar")
        textview.makeLinks(this, Link(
            linkText = "Link1",
            isUnderlineLinkText = true,
            linkColor = R.color.design_default_color_error)
            , Link("Link2", {
            this.showToast("Link2")
        })
        )


    }
}