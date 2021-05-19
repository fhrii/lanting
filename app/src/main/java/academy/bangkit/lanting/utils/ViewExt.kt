package academy.bangkit.lanting.utils

import academy.bangkit.lanting.R
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target

fun EditText.setOnChangeListener(callback: (text: String?) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            callback(s?.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun afterTextChanged(s: Editable?) {}
    })
}

fun ImageView.setImageFromUrl(url: String) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.ic_image_loading)
        .override(Target.SIZE_ORIGINAL)
        .into(this)
}