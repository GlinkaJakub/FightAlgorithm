package com.glinka.util;

public interface Answer {

    interface ConfigAnswer extends Answer {}
    interface BlackSpotsAnswer extends Answer {}
    interface MoveAnswer extends Answer {}

    void notify(String answer);
}
