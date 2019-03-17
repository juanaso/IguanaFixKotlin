package challenge.juanaso.com.iguanafixkotlin.utils.bindingadapters

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.BindingAdapter
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import challenge.juanaso.com.iguanafixkotlin.utils.extension.getParentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import challenge.juanaso.com.iguanafixkotlin.R
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou


@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}

@BindingAdapter("photoUrl")
fun setPhotoUrl(view: ImageView, getPhotoUrl: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && getPhotoUrl != null) {
        getPhotoUrl.observe(parentActivity, Observer { value ->
            if (!value!!.endsWith(".svg")) {
                val options = RequestOptions()
                        .centerCrop()
                        .placeholder(R.mipmap.ic_launcher_round)
                        .error(R.mipmap.ic_launcher_round)
                Glide.with(parentActivity).load(value).apply(options).into(view)
            }else {
                GlideToVectorYou.justLoadImage(parentActivity, Uri.parse(value), view)
            }

            //view.text = value?:""
        })
    }
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}