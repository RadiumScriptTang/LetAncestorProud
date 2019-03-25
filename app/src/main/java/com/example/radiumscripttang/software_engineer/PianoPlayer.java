package com.example.radiumscripttang.software_engineer;

import android.app.ListActivity;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class PianoUnit{
    int noteId;
    int delay;
    public PianoUnit(int id, int delay){
        this.noteId = id;
        this.delay = delay;
    }
}

class PianoSong{
    ArrayList<PianoUnit> song = new ArrayList<>();
    StringBuilder songString = new StringBuilder();
    public PianoSong(){

    }
    public int save(){
        return 0;
    }

    public int load(){
        return 0;
    }

    public void append(int id, int delay){
        PianoUnit unit = new PianoUnit(id ,delay);
        song.add(unit);
    }

    public void backspace(){
        song.remove(song.size() - 1);
    }
}

public class PianoPlayer {
    AudioAttributes audioAttributes = new AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build();
    SoundPool soundPool = new SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .setMaxStreams(35)
            .build();
    HashMap <Integer, Integer> soundMap = new HashMap<>();
    private void loadMusci(Context context){
        soundMap.put(0,soundPool.load(context,R.raw.a2, 1));
        soundMap.put(1,soundPool.load(context,R.raw.a3, 1));
        soundMap.put(2,soundPool.load(context,R.raw.a4, 1));
        soundMap.put(3,soundPool.load(context,R.raw.a5, 1));
        soundMap.put(4,soundPool.load(context,R.raw.a6, 1));
        soundMap.put(5,soundPool.load(context,R.raw.a7, 1));
        soundMap.put(6,soundPool.load(context,R.raw.b1, 1));
        soundMap.put(7,soundPool.load(context,R.raw.b2, 1));
        soundMap.put(8,soundPool.load(context,R.raw.b3, 1));
        soundMap.put(9,soundPool.load(context,R.raw.b4, 1));
        soundMap.put(10,soundPool.load(context,R.raw.b5, 1));
        soundMap.put(11,soundPool.load(context,R.raw.b6, 1));
        soundMap.put(12,soundPool.load(context,R.raw.b7, 1));
        soundMap.put(13,soundPool.load(context,R.raw.c1, 1));
        soundMap.put(14,soundPool.load(context,R.raw.c2, 1));
        soundMap.put(15,soundPool.load(context,R.raw.c3, 1));
        soundMap.put(16,soundPool.load(context,R.raw.c4, 1));
        soundMap.put(17,soundPool.load(context,R.raw.c5, 1));
        soundMap.put(18,soundPool.load(context,R.raw.c6, 1));
        soundMap.put(19,soundPool.load(context,R.raw.c7, 1));
        soundMap.put(20,soundPool.load(context,R.raw.d1, 1));
        soundMap.put(21,soundPool.load(context,R.raw.a2_, 1));
        soundMap.put(22,soundPool.load(context,R.raw.a4_, 1));
        soundMap.put(23,soundPool.load(context,R.raw.a5_, 1));
        soundMap.put(24,soundPool.load(context,R.raw.a6_, 1));
        soundMap.put(25,soundPool.load(context,R.raw.b1_, 1));
        soundMap.put(26,soundPool.load(context,R.raw.b2_, 1));
        soundMap.put(27,soundPool.load(context,R.raw.b4_, 1));
        soundMap.put(28,soundPool.load(context,R.raw.b5_, 1));
        soundMap.put(29,soundPool.load(context,R.raw.b6_, 1));
        soundMap.put(30,soundPool.load(context,R.raw.c1_, 1));
        soundMap.put(31,soundPool.load(context,R.raw.c2_, 1));
        soundMap.put(32,soundPool.load(context,R.raw.c4_, 1));
        soundMap.put(33,soundPool.load(context,R.raw.c5_, 1));
        soundMap.put(34,soundPool.load(context,R.raw.c6_, 1));
    }
    public PianoPlayer(Context context){
        loadMusci(context);
    }
    public void play(int id){
        soundPool.play(id,1,1,1,0,1);
    }
}
