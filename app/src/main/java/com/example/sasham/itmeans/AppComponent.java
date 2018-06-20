package com.example.sasham.itmeans;

import android.content.Context;

import com.example.sasham.itmeans.data.DataModule;
import com.example.sasham.itmeans.data.network.ApiModule;
import com.example.sasham.itmeans.data.db.RealmModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        ApiModule.class,
        RealmModule.class,
        DataModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder context(Context context);

        AppComponent build();
    }

    void injectApp(BaseApplication baseApplication);
}
