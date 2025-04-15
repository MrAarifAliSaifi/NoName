import android.content.Context
import com.example.nonames.di.AppModule
import com.example.nonames.di.NetworkModule
import com.example.nonames.ui.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Context): AppComponent
    }

    fun inject(activity: MainActivity)
}
