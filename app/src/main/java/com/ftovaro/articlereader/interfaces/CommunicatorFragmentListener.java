package com.ftovaro.articlereader.interfaces;

/**
 * Interface that helps to send data.
 * Created by ftova on 10-Mar-16.
 */
public interface CommunicatorFragmentListener {
    /**
     * Informs with a boolean if there is need to change
     * the colors of the cards of the recycler view.
     * @param state true if the color needs to be change, otherwise, false.
     */
    void changeCardsColors(boolean state);
}
