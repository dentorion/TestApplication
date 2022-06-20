package com.entin.testapplication.screens.main.epoxy

import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.entin.domain.model.UserDomain
import com.entin.testapplication.R
import com.entin.testapplication.screens.main.MainScreenFragment
import com.entin.testapplication.screens.main.MainScreenFragmentDirections
import com.uhero.core.epoxy.EpoxyKotlinHolder

/**
 * ViewHolder for RecyclerView of all users
 */

@EpoxyModelClass(layout = R.layout.epoxy_user_view)
abstract class UserModel : EpoxyModelWithHolder<UserHolder>() {

    @EpoxyAttribute
    lateinit var userDomainModel: UserDomain

    @EpoxyAttribute
    lateinit var onClick: () -> Unit

    override fun bind(holder: UserHolder) {
        Glide.with(holder.imageView)
            .load(userDomainModel.avatarUrl)
            .transform(CenterCrop())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.imageView)

        holder.apply {
            nameView.text = userDomainModel.name
            bodyView.setOnClickListener {
                onClick()
            }
        }
    }
}

class UserHolder : EpoxyKotlinHolder() {
    val imageView by bind<ImageView>(R.id.epoxy_user_avatar)
    val nameView by bind<TextView>(R.id.epoxy_user_name)
    val bodyView by bind<ConstraintLayout>(R.id.user_body_view)
}