package com.example.awesome.programmiernacht;

import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by tim on 04.04.15.
 */
public class WordCardProvider {

    public WordCardProvider(XmlResourceParser xpp) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            xpp.next();

            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {
                    stringBuffer.append("--- Start XML ---");
                } else if (eventType == XmlPullParser.START_TAG) {
                    stringBuffer.append("\nSTART_TAG: " + xpp.getName());
                } else if (eventType == XmlPullParser.END_TAG) {
                    stringBuffer.append("\nEND_TAG: " + xpp.getName());
                } else if (eventType == XmlPullParser.TEXT) {
                    stringBuffer.append("\nTEXT: " + xpp.getText());
                }
            }
            stringBuffer.append("\n--- End XML ---");
            String result = stringBuffer.toString();

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WordCard GetNextCard(int difficulty) {
        return new WordCard();
    }
}
