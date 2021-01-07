package com.omralcorut.githubsearcher.ui.search

import com.airbnb.epoxy.Typed2EpoxyController
import com.omralcorut.githubsearcher.R
import com.omralcorut.githubsearcher.models.Repository
import com.omralcorut.githubsearcher.models.User

class SearchEpoxyController(
    var adapterCallbacks: SearchEpoxyAdapterCallbacks
) :
    Typed2EpoxyController<List<User>, List<Repository>>() {

    override fun buildModels(
        data1: List<User>,
        data2: List<Repository>
    ) {
        if (data1.isNotEmpty()) {
            CategoryTitleEpoxyModel_()
                .id("user_title")
                .titleId(R.string.users)
                .addTo(this)
        }
        data1.forEach {
            UserEpoxyModel_()
                .id(it.id)
                .user(it)
                .clickListener { model, _, _, _ ->
                    adapterCallbacks.userClick(model.user!!)
                }
                .addTo(this)
        }

        if (data2.isNotEmpty()) {
            CategoryTitleEpoxyModel_()
                .id("repository_title")
                .titleId(R.string.repositories)
                .addTo(this)
        }
        data2.forEach {
            RepositoryEpoxyModel_()
                .id(it.id)
                .repository(it)
                .clickListener { model, _, _, _ ->
                    adapterCallbacks.repositoryClick(model.repository!!)
                }
                .addTo(this)
        }
    }

    interface SearchEpoxyAdapterCallbacks {
        fun userClick(user: User)
        fun repositoryClick(repository: Repository)
    }
}