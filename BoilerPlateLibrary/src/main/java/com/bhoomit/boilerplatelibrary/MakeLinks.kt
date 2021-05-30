package com.bhoomit.boilerplatelibrary

import android.content.Context
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat

object MakeLinks {
    fun TextView.makeLinks(context : Context,vararg links : Link){
        val spannableString = SpannableString(this.text)
        var startIndexOfLink = -1
        for(link in links){
            val clickableSpan = object : ClickableSpan(){
                override fun updateDrawState(textPaint: TextPaint) {
                    super.updateDrawState(textPaint)
                    textPaint.color = ContextCompat.getColor(context,link.linkColor)
                    textPaint.isUnderlineText = link.isUnderlineLinkText
                }
                override fun onClick(view: View) {
                    Selection.setSelection((view as TextView).text as Spannable,0)
                    view.invalidate()
                    link.onClickListener.onClick(view)
                }
            }
            startIndexOfLink = this.text.toString().indexOf(link.linkText,startIndexOfLink+1)
            spannableString.setSpan(clickableSpan,startIndexOfLink,startIndexOfLink+link.linkText.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        this.movementMethod = LinkMovementMethod.getInstance()
        this.setText(spannableString,TextView.BufferType.SPANNABLE)
    }
}

data class Link(
    val linkText : String,
    val onClickListener: View.OnClickListener = View.OnClickListener{},
    val linkColor : Int = R.color.design_default_color_secondary_variant,
    val isUnderlineLinkText : Boolean = false
)