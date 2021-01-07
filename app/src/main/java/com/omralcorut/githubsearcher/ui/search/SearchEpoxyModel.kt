package com.omralcorut.githubsearcher.ui.search

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.airbnb.epoxy.*
import com.omralcorut.githubsearcher.R
import com.omralcorut.githubsearcher.models.Repository
import com.omralcorut.githubsearcher.models.User

@EpoxyModelClass(layout = R.layout.holder_category_title)
abstract class CategoryTitleEpoxyModel : EpoxyModelWithHolder<CategoryTitleEpoxyModel.Holder>() {
    @EpoxyAttribute
    var titleId: Int? = null

    override fun bind(holder: Holder) {
        holder.titleTextView.text = holder.titleTextView.context.getString(titleId!!)
    }

    class Holder : EpoxyHolder() {
        lateinit var titleTextView: TextView

        override fun bindView(itemView: View) {
            this.titleTextView = itemView.findViewById(R.id.text_view_title)
        }
    }
}

@EpoxyModelClass(layout = R.layout.holder_user)
abstract class UserEpoxyModel : EpoxyModelWithHolder<UserEpoxyModel.Holder>() {

    @EpoxyAttribute
    var user: User? = null

    @EpoxyAttribute
    lateinit var clickListener: View.OnClickListener

    override fun bind(holder: Holder) {
        holder.avatarImageView.load(user?.avatar_url)
        holder.userNameTextView.text = user?.login
        holder.itemView.setOnClickListener(clickListener)
    }

    class Holder : EpoxyHolder() {
        lateinit var itemView: View
        lateinit var avatarImageView: ImageView
        lateinit var userNameTextView: TextView

        override fun bindView(itemView: View) {
            this.itemView = itemView
            this.avatarImageView = itemView.findViewById(R.id.image_view_avatar)
            this.userNameTextView = itemView.findViewById(R.id.text_view_user_name)
        }
    }
}

@EpoxyModelClass(layout = R.layout.holder_repository)
abstract class RepositoryEpoxyModel : EpoxyModelWithHolder<RepositoryEpoxyModel.Holder>() {

    @EpoxyAttribute
    var repository: Repository? = null

    @EpoxyAttribute
    lateinit var clickListener: View.OnClickListener

    override fun bind(holder: Holder) {
        holder.repoNameTextView.text = repository?.full_name
        holder.descriptionTextView.text = repository?.description
        holder.itemView.setOnClickListener(clickListener)
    }

    class Holder : EpoxyHolder() {
        lateinit var itemView: View
        lateinit var repoNameTextView: TextView
        lateinit var descriptionTextView: TextView

        override fun bindView(itemView: View) {
            this.itemView = itemView
            this.repoNameTextView = itemView.findViewById(R.id.text_view_repo_name)
            this.descriptionTextView = itemView.findViewById(R.id.text_view_description)
        }
    }
}