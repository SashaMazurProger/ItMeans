package com.example.sasham.itmeans;


import com.example.sasham.itmeans.search.WordDetailsInteractorImp;
import com.example.sasham.itmeans.search.WordDetailsViewModel;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class WordDetailsViewModelTest {

    @Mock
    WordDetailsInteractorImp detailsInteractorImp;

    WordDetailsViewModel detailsViewModel;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        detailsViewModel=spy(new WordDetailsViewModel(detailsInteractorImp));
    }

    @Test
    void notNull(){
        Assertions.assertNotNull(detailsInteractorImp);
        Assertions.assertNotNull(detailsViewModel);
    }

    @Test
    void callIsFavorite(){
        detailsViewModel.search("word");
        verify(detailsInteractorImp.getWordDefinition("word"));
    }
}
