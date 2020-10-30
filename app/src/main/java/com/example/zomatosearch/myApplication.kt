package com.example.zomatosearch

import android.app.Application
import com.example.zomatosearch.network.myApi
import com.example.zomatosearch.repos.searchRepo
import com.example.zomatosearch.ui.SearchViewModelFactory
import com.example.zomatosearch.viewmodel.SearchViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import kotlin.math.sin

class myApplication: Application(),KodeinAware{
    override val kodein= Kodein.lazy {
       import(androidXModule(this@myApplication))
        bind() from singleton { SearchViewModel(instance(),instance()) }
       bind() from singleton { searchRepo() }
        bind() from singleton { myApi(instance(),instance(),instance(),instance()) }
        bind() from provider { SearchViewModelFactory(instance(),instance()) }
    }
}