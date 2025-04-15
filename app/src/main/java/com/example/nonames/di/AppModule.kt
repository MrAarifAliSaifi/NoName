package com.example.nonames.di

import com.example.nonames.data.repository.GithubRepo
import com.example.nonames.ui.main.GithubViewModel
import com.example.nonames.ui.main.GithubViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideGithubViewModel(repository: GithubRepo): GithubViewModel {
        return GithubViewModel(repository)
    }

    @Provides
    fun provideGithubViewModelFactory(viewModel: GithubViewModel): GithubViewModelFactory {
        return GithubViewModelFactory(viewModel)
    }
}
