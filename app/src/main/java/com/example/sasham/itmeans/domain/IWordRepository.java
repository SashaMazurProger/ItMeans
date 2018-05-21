package com.example.sasham.itmeans.domain;

import java.util.List;

/**
 * Created by Sasha M on 14.04.2018.
 */

public interface IWordRepository {
    public io.reactivex.Observable<List<String>> getAutoEndedWords(String word);
}
