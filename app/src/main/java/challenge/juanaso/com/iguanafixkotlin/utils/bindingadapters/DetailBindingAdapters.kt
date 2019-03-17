package challenge.juanaso.com.iguanafixkotlin.utils.bindingadapters

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.BindingAdapter
import android.net.Uri
import challenge.juanaso.com.iguanafixkotlin.R
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import challenge.juanaso.com.iguanafixkotlin.model.User
import challenge.juanaso.com.iguanafixkotlin.utils.extension.getParentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

@BindingAdapter("userPhotoUrl")
fun setUserPhotoUrl(view: ImageView, user: MutableLiveData<User>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null) {
        user?.observe(parentActivity, Observer { value ->
            if (!user.value?.photoUrl!!.endsWith(".svg")) {
                val options = RequestOptions()
                        .centerCrop()
                        .placeholder(R.mipmap.ic_launcher_round)
                        .error(R.mipmap.ic_launcher_round)
                Glide.with(parentActivity).load(value?.photoUrl).apply(options).into(view)
            }else {
                GlideToVectorYou.justLoadImage(parentActivity, Uri.parse(user.value?.photoUrl), view)
            }
        })
    }
}
