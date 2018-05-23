package com.example.sasham.itmeans.data;

import com.example.sasham.itmeans.data.network.NetworkWordRepository;

public class RepositoryProvider {
    public static WordRepository getDefaultWordRepository(){
        return new NetworkWordRepository();
    }
}
