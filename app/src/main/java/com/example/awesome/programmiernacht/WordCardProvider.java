package com.example.awesome.programmiernacht;

import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

/**
 * Created by tim on 04.04.15.
 */
public class WordCardProvider {
    private LinkedList<WordCard> m_wordCards = new LinkedList<>();
    public enum Difficulty {
        EASY,
        MIDDLE,
        HARD
    }
    public WordCardProvider(XmlResourceParser parser) {
        LoadCards(parser);
        MixCards();
    }


    private void LoadCards(XmlResourceParser parser)
    {
        try {
            parser.next();
            String name = "", text = "";
            WordCard wordCard = new WordCard();

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {
                } else if (eventType == XmlPullParser.START_TAG) {
                    name = parser.getName();
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (name.equals("Word")) {
                        wordCard.SetWord(text);
                    } else if (name.equals("ForbiddenWord")) {
                        wordCard.AddForbiddenWord(text);
                    } else if (name.equals("Difficulty"))
                    {
                        int difficulty = (int)(Integer.parseInt(text));
                        wordCard.SetDifficulty(difficulty);
                    } else {
                        name = "";
                    }
                    if (parser.getName().equals("WordCard"))
                    {
                        m_wordCards.add(wordCard);
                        wordCard = new WordCard();
                    }
                } else if (eventType == XmlPullParser.TEXT) {
                    text = parser.getText().replace("\n","").trim();
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO: All cards should be available again (if game is restarted inside app).
    public void reset() {

    }

    public void MixCards()
    {
        Collections.shuffle(m_wordCards);
    }

    public WordCard GetNextCard(Difficulty difficulty) {
        return GetNextCard(difficulty.ordinal());
    }

    public WordCard GetNextCard(int difficulty) {

        for (WordCard wordCard : m_wordCards)
        {
            if (wordCard.GetDifficulty() == difficulty)
            {
                m_wordCards.remove(wordCard);

                return wordCard;
            }
        }

        List<String> forbiddenWords = new LinkedList<String>();
        forbiddenWords.add("Oooh");
        forbiddenWords.add("maaan");
        forbiddenWords.add(":(");
        return new WordCard("Karten sind aus!", forbiddenWords,difficulty);
    }
}
