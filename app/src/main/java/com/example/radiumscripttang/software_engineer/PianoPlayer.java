package com.example.radiumscripttang.software_engineer;

import android.app.ListActivity;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.LoginFilter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.ImageButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PianoUnit{
    int noteId;
    int delay;
    public PianoUnit(int id, int delay){
        this.noteId = id;
        this.delay = delay;
    }

    public int getNoteId() {
        return noteId;
    }

    public int getDelay() {
        return delay;
    }
}

class PianoSong {
    ArrayList<PianoUnit> song = new ArrayList<PianoUnit>();
    int noteSpans [] = {R.mipmap.n0_1000,R.mipmap.n1_1000, R.mipmap.n2_1000, R.mipmap.n3_1000, R.mipmap.n4_1000, R.mipmap.n5_1000,
            R.mipmap.n6_1000, R.mipmap.n7_1000, R.mipmap.n8_1000, R.mipmap.n9_1000, R.mipmap.n10_1000, R.mipmap.n11_1000
            , R.mipmap.n12_1000, R.mipmap.n13_1000, R.mipmap.n14_1000, R.mipmap.n15_1000, R.mipmap.n16_1000, R.mipmap.n17_1000,
            R.mipmap.n18_1000, R.mipmap.n19_1000, R.mipmap.n20_1000,R.mipmap.n0_500, R.mipmap.n1_500, R.mipmap.n2_500, R.mipmap.n3_500,
            R.mipmap.n4_500, R.mipmap.n5_500, R.mipmap.n6_500, R.mipmap.n7_500, R.mipmap.n8_500, R.mipmap.n9_500, R.mipmap.n10_500, R.mipmap.n11_500,
            R.mipmap.n12_500, R.mipmap.n13_500, R.mipmap.n14_500, R.mipmap.n15_500, R.mipmap.n16_500, R.mipmap.n17_500, R.mipmap.n18_500, R.mipmap.n19_500, R.mipmap.n20_500,
            R.mipmap.n0_250, R.mipmap.n1_250, R.mipmap.n2_250, R.mipmap.n3_250, R.mipmap.n4_250, R.mipmap.n5_250, R.mipmap.n6_250,
            R.mipmap.n7_250, R.mipmap.n8_250, R.mipmap.n9_250, R.mipmap.n10_250, R.mipmap.n11_250, R.mipmap
            .n12_250, R.mipmap.n13_250, R.mipmap.n14_250, R.mipmap.n15_250, R.mipmap.n16_250, R.mipmap.n17_250, R.mipmap
            .n18_250, R.mipmap.n19_250, R.mipmap.n20_250};

    String spanString[] = {"n0_1000,", "n1_1000,", "n2_1000,", "n3_1000,", "n4_1000,", "n5_1000,", "n6_1000,", "n7_1000,", "n8_1000,", "n9_1000,", "n10_1000,",
            "n11_1000,", "n12_1000,", "n13_1000,", "n14_1000,", "n15_1000,", "n16_1000,", "n17_1000,", "n18_1000,", "n19_1000,", "n20_1000,","n0_500,", "n1_500,",
            "n2_500,", "n3_500,", "n4_500,", "n5_500,", "n6_500,", "n7_500,", "n8_500,", "n9_500,", "n10_500,", "n11_500,", "n12_500,", "n13_500,",
            "n14_500,", "n15_500,", "n16_500,", "n17_500,", "n18_500,", "n19_500,", "n20_500,","n0_250,", "n1_250,", "n2_250,", "n3_250,", "n4_250,",
            "n5_250,", "n6_250,", "n7_250,", "n8_250,", "n9_250,", "n10_250,",
            "n11_250,", "n12_250,", "n13_250,", "n14_250,", "n15_250,", "n16_250,", "n17_250,", "n18_250,", "n19_250,", "n20_250,"
    };

    Context context;

    Thread playThread;

    public PianoSong(Context context){
        this.context = context;

    }
    public void empty(){
        this.song.clear();
    }
    public void loadSong(String string){
        ArrayList<PianoUnit> pianoSong = new ArrayList<>();
        int j = 0;

        while (j < string.length()){
            if (string.charAt(j) == 'n'){
                int i = j + 1;
                StringBuilder number = new StringBuilder();
                StringBuilder delay = new StringBuilder();
                while (string.charAt(i) != '_'){
                    number.append(string.charAt(i++));
                }
                i += 1;
                while (string.charAt(i) != ','){
                    delay.append(string.charAt(i++));
                }
                pianoSong.add(new PianoUnit(Integer.parseInt(number.toString()),Integer.parseInt(delay.toString())));
            }
            j++;
        }
        this.song = pianoSong;
    }


    public void append(int id, int delay){
        PianoUnit unit = new PianoUnit(id ,delay);
        song.add(unit);
    }

    public void backspace(){
        if (song.size() == 0){
            return;
        }
        song.remove(song.size() - 1);
    }
    public StringBuilder getString(){
        StringBuilder stringBuilder = new StringBuilder();
        for( PianoUnit u : song){
            stringBuilder.append("n"+u.getNoteId()+"_"+u.getDelay()+",");
        }
        return stringBuilder;
    }
    public SpannableString getSpanString(StringBuilder string){
        SpannableString spannableString = new SpannableString(string);
        for(int i = 0; i < noteSpans.length; i++){
            Pattern pattern = Pattern.compile(spanString[i]);
            Matcher matcher = pattern.matcher(string);
            while (matcher.find()){
                spannableString.setSpan(new ImageSpan(context,noteSpans[i]),matcher.start(),matcher.end(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return  spannableString;
    }
    public int length(){
        return song.size();
    }
    public void play(PianoPlayer player, ImageButton start){
        start.setImageResource(R.mipmap.stop);

        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0){
                    start.setImageResource(R.mipmap.play);
                }
            }
        };
        playThread = new Thread(){
            @Override
            public void run() {
                for( PianoUnit u : song){
                    player.play(u.getNoteId());
                    try {
                        Thread.sleep(u.getDelay());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Message msg = new Message();
                        msg.what = 0;
                        handler.sendMessage(msg);
                        break;
                    }
                }
                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        };
        playThread.start();


    }
    public void stop(){
        playThread.interrupt();
    }
    public PianoUnit getLast(){
        if (length() == 0){
            return null;
        }
        return song.get(length() - 1);
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
        soundPool.play(id + 1,1,1,1,0,1);
    }
}
