package com.example.sasham.itmeans.data.network.offline;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dagger.Module;
import dagger.Provides;
import dagger.producers.Production;
import dagger.producers.ProductionComponent;

//@ProductionComponent(modules = {OfflineModule.class,ExecutorModule.class})
//public interface OfflineComponent {
//
//    ListenableFuture<OfflineDictionaryManager> offlineDictionaryManager();
//}
//
//@Module
//final class ExecutorModule {
//    @Provides
//    @Production
//    static Executor executor() {
//        return Executors.newCachedThreadPool();
//    }
//}