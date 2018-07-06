package com.example.android.tones;

public class Audio {

    private String mSong_name;
    private String mArtist;
    private String mPath;
    private String mDuration;

    public String getmDuration() {
        return mDuration;
    }

    public Audio(String mSong_name, String mArtist, String mDuration, String mPath) {
        this.mSong_name = mSong_name;
        this.mArtist = mArtist;
        this.mPath = mPath;
        this.mDuration = mDuration;
    }

    public String getmPath() {
        return mPath;
    }

    public Audio(String mSong_name, String mArtist, String mPath) {

        this.mSong_name = mSong_name;
        this.mArtist = mArtist;
        this.mPath = mPath;
    }

    public Audio(String mSong_name, String mArtist) {
        this.mSong_name = mSong_name;
        this.mArtist = mArtist;

    }

    public String getmSong_name() {
        return mSong_name;
    }

    public String getmArtist() {
        return mArtist;
    }
}
