package com.example.android.miwok;

public class Word {
    private String miwok;
    private String english;
    private int imageID;
    private int audioID;

    public Word(String miwok, String english, int imageID, int audioID) {
        this.miwok = miwok;
        this.english = english;
        this.imageID = imageID;
        this.audioID = audioID;
    }

    public Word(String miwok, String english, int audioID) {
        this.miwok = miwok;
        this.english = english;
        this.imageID=0;
        this.audioID=audioID;
    }


    public String getMiwokTranslation()
    {
        return miwok;
    }

    public String getDefaultTranslation(   )
    {
        return english;
    }

    public int getImageID() {
        return imageID;
    }

    public int getAudioID() {
        return audioID;
    }
}
