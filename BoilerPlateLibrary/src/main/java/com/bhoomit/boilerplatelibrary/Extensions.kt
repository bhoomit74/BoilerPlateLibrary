package com.bhoomit.boilerplatelibrary

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.renderscript.ScriptGroup
import android.text.Selection
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import java.io.*
import java.lang.Exception
import java.net.URI



//Toast and SnackBar Extensions
fun Context.showToast(message : String){
      Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}
fun View.snackBar(message: String){
      Snackbar.make(this,message,Snackbar.LENGTH_SHORT).show()
}



//Visibility Extensions
fun View.visible(){
      this.visibility = View.VISIBLE
}
fun View.hide(){
      this.visibility = View.GONE
}
fun View.invisible(){
      this.visibility = View.INVISIBLE
}
fun View.setVisibility(isVisible : Boolean){
      this.visibility = if(isVisible) View.VISIBLE else View.GONE
}



//Glide Extensions
fun ImageView.src(context : Context,stringImageURL : String){
      Glide
            .with(context)
            .load(stringImageURL)
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_image_not_supported_24)
            .into(this);
}
fun ImageView.src(context : Context,imageUri : URI){
      Glide
            .with(context)
            .load(imageUri)
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_image_not_supported_24)
            .into(this);
}
fun ImageView.src(context : Context,bitmap : Bitmap){
      Glide
            .with(context)
            .load(bitmap)
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_image_not_supported_24)
            .into(this);
}


fun Activity.hideKeyboard(){
      val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
      var view = this.currentFocus
      if (view == null){
            view = View(this)
      }
      inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
}


fun Context.showAlertDialog(title : String,message : String,positiveButtonText : String,negativeButtonText : String){
      val alertDialog = this.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                  setNegativeButton(negativeButtonText){
                        dialog,_->
                        dialog.dismiss()
                  }
            }

            builder.setTitle(title)
            builder.setMessage(message)
            builder.create()
      }
      alertDialog.show()
}
fun copyFile(inputStream : InputStream,outputPath : String) : String{
      try {
          val input : InputStream?
          val output : OutputStream?

          val dir = File(outputPath)
            if (!dir.exists()){
                  dir.createNewFile()
            }
            input = inputStream
            output = FileOutputStream(outputPath)
            val buffer = ByteArray(65536)
            var read :Int
            while (input.read(buffer).also { read = it } != -1){
                  output.write(buffer,0,read)
            }
            input.close()
            output.flush()
            output.fd.sync()
            output.close()
            return "File copied successfully"
      }catch (fileNotFoundException : FileNotFoundException){
            return "File not found"
      }
      catch (exception : Exception){
            return exception.message.toString()
      }
}
fun copyFile(inputPath : String,outputPath : String) : String{
      try {
            val input : InputStream?
            val output : OutputStream?

            val dir = File(outputPath)
            if (!dir.exists()){
                  dir.createNewFile()
            }
            input = FileInputStream(inputPath)
            output = FileOutputStream(outputPath)
            val buffer = ByteArray(65536)
            var read :Int
            while (input.read(buffer).also { read = it } != -1){
                  output.write(buffer,0,read)
            }
            input.close()
            output.flush()
            output.fd.sync()
            output.close()
            return "File copied successfully"
      }catch (fileNotFoundException : FileNotFoundException){
            return "File not found"
      }
      catch (exception : Exception){
            return exception.message.toString()
      }
}


fun openFileManager(activity : Activity,requestCode : Int){
      val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"
      }
      activity.startActivityForResult(intent,requestCode)
}
fun openCamera(activity: Activity,requestCode: Int){
      val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
      activity.startActivityForResult(intent,requestCode)
}
fun openGallery(activity: Activity,requestCode: Int,title : String = "Gallery"){
      val intent = Intent()
      intent.type = "image/*"
      intent.action = Intent.ACTION_PICK
      activity.startActivityForResult(Intent.createChooser(intent,title),requestCode)
}

