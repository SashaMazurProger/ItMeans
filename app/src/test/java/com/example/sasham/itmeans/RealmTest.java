package com.example.sasham.itmeans;

import android.content.Context;

import com.example.sasham.itmeans.data.db.model.FavoriteWord;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.realm.Realm;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RealmTest {

    @Mock
    public Realm realm=mock(Realm.class);

    @BeforeEach
    public void createRealm() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInsertFavoriteWord(){
        FavoriteWord favoriteWord=new FavoriteWord();

        when(realm.copyToRealm(favoriteWord)).thenReturn(favoriteWord);

        realm.copyToRealm(favoriteWord);

        verify(realm,atLeastOnce()).copyToRealm(favoriteWord);

    }
}
