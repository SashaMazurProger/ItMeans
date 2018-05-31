package com.example.sasham.itmeans.data.network;

import android.content.Context;

import dagger.Component;

@TwinWordScope
@Component(modules = {ApiModule.class})
public interface TwinWordComponent {
    TwinWordApi getTwinWordApi();
}
