package com.bminor.officebarkaroake;

/**
 * Created by Brett on 1/7/16.
 */
public class SongInfo {

    private String _song;
    private String _artist;

    public String get_artist() { return ( _artist.length() > 0 ? "by " +_artist : _artist ); }

    public void set_artist(String _artist) {
        this._artist = _artist;
    }

    public String get_song() {
        return _song;
    }

    public void set_song(String _song) {
        this._song = _song;
    }

    @Override
    public String toString(){
        return (_song + " - " + _artist);
    }

}
