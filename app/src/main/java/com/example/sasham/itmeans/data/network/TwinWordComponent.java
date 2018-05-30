package com.example.sasham.itmeans.data.network;

import dagger.Component;

@TwinWordScope
@Component(modules = {ApiModule.class})
public interface TwinWordComponent {
    TwinWordApi getTwinWordApi();
}
