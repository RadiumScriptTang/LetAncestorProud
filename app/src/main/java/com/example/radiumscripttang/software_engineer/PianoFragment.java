package com.example.radiumscripttang.software_engineer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.menu.MenuView;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class PianoFragment extends Fragment {
    ImageButton btn_cal;

    ImageButton btn_re_minus, btn_mi_minus, btn_fa_minus, btn_so_minus, btn_la_minus, btn_si_minus,
            btn_do,btn_re,btn_mi,btn_fa,btn_so,btn_la,btn_si,btn_do_plus,btn_re_plus,btn_mi_plus,btn_fa_plus,btn_so_plus,btn_la_plus,btn_si_plus,
            btn_do_plus_plus;
    PianoPlayer pianoPlayer;
    ImageButton btn_del, btn_play, btn_diy, btn_save, btn_load;
    TextView e_input;
    PianoSong pianoSong;
    ListView file_list;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    Map<String,?> map;



    private boolean isDiy;
    private boolean isLoading;
    private boolean isPlaying;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private View.OnClickListener pianoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.re_minus:
                    pianoPlayer.play(0);
                    break;
                case R.id.mi_minus:
                    pianoPlayer.play(1);
                    break;
                case R.id.fa_minus:
                    pianoPlayer.play(2);
                    break;
                case R.id.so_minus:
                    pianoPlayer.play(3);
                    break;
                case R.id.la_minus:
                    pianoPlayer.play(4);
                    break;
                case R.id.si_minus:
                    pianoPlayer.play(5);
                    break;
                case R.id.doo:
                    pianoPlayer.play(6);
                    break;
                case R.id.re:
                    pianoPlayer.play(7);
                    break;
                case R.id.mi:
                    pianoPlayer.play(8);
                    break;
                case R.id.fa:
                    pianoPlayer.play(9);
                    break;
                case R.id.so:
                    pianoPlayer.play(10);
                    break;
                case R.id.la:
                    pianoPlayer.play(11);
                    break;
                case R.id.si:
                    pianoPlayer.play(12);
                    break;
                case R.id.do_plus:
                    pianoPlayer.play(13);
                    break;
                case R.id.re_plus:
                    pianoPlayer.play(14);
                    break;
                case R.id.mi_plus:
                    pianoPlayer.play(15);
                    break;
                case R.id.fa_plus:
                    pianoPlayer.play(16);
                    break;
                case R.id.so_plus:
                    pianoPlayer.play(17);
                    break;
                case R.id.la_plus:
                    pianoPlayer.play(18);
                    break;
                case R.id.si_plus:
                    pianoPlayer.play(19);
                    break;
                case R.id.do_plus_plus:
                    pianoPlayer.play(20);
                    break;
            }

            if (isDiy){
                switch (view.getId()){
                    case R.id.re_minus:
                        pianoSong.append(0,1000);
                        break;
                    case R.id.mi_minus:
                        pianoSong.append(1,1000);
                        break;
                    case R.id.fa_minus:
                        pianoSong.append(2,1000);
                        break;
                    case R.id.so_minus:
                        pianoSong.append(3,1000);
                        break;
                    case R.id.la_minus:
                        pianoSong.append(4,1000);
                        break;
                    case R.id.si_minus:
                        pianoSong.append(5,1000);
                        break;
                    case R.id.doo:
                        pianoSong.append(6,1000);
                        break;
                    case R.id.re:
                        pianoSong.append(7,1000);
                        break;
                    case R.id.mi:
                        pianoSong.append(8,1000);
                        break;
                    case R.id.fa:
                        pianoSong.append(9,1000);
                        break;
                    case R.id.so:
                        pianoSong.append(10,1000);
                        break;
                    case R.id.la:
                        pianoSong.append(11,1000);
                        break;
                    case R.id.si:
                        pianoSong.append(12,1000);
                        break;
                    case R.id.do_plus:
                        pianoSong.append(13,1000);
                        break;
                    case R.id.re_plus:
                        pianoSong.append(14,1000);
                        break;
                    case R.id.mi_plus:
                        pianoSong.append(15,1000);
                        break;
                    case R.id.fa_plus:
                        pianoSong.append(16,1000);
                        break;
                    case R.id.so_plus:
                        pianoSong.append(17,1000);
                        break;
                    case R.id.la_plus:
                        pianoSong.append(18,1000);
                        break;
                    case R.id.si_plus:
                        pianoSong.append(19,1000);
                        break;
                    case R.id.do_plus_plus:
                        pianoSong.append(20,1000);
                        break;
                }
                e_input.setText(pianoSong.getSpanString(pianoSong.getString()));
            }
        }
    };
    private View.OnClickListener ioOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.save_song:
                    if (pianoSong.length() == 0){
                        return;
                    }
                    String songString  = pianoSong.getString().toString();
                    final EditText et = new EditText(getActivity());
                    et.setText("未命名");
                    new AlertDialog.Builder(getActivity()).setTitle("保存")
                            .setView(et)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String name = et.getText().toString();
                                    if (name.equals("")){
                                        Toast.makeText(getActivity(),"命名有误",Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                    String nameStored = sharedPreferences.getString(name,"");
                                    if (!nameStored.equals("")){
                                        Toast.makeText(getActivity(),"文件已存在",Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                    editor.putString(name,songString);
                                    editor.apply();

                                }
                            }).setNegativeButton("取消",null).show();
                    break;
                case R.id.load_song:
                    isLoading = true;
                    isDiy = false;
                    e_input.setVisibility(View.INVISIBLE);
                    file_list.setVisibility(View.VISIBLE);
                    String[] savedString = map.keySet().toArray(new String[map.keySet().size()]);
                    ArrayAdapter<String> adpter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,savedString);
                    file_list.setAdapter(adpter);
            }
        }
    };
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String loadingFile = ((TextView) getActivity().findViewById(view.getId())).getText().toString();
            String songString = sharedPreferences.getString(loadingFile,"");
            pianoSong.empty();
            Log.i("SONGSTRING", "onItemClick: " + songString);
            pianoSong.loadSong(songString);
            e_input.setVisibility(View.VISIBLE);
            file_list.setVisibility(View.INVISIBLE);
            e_input.setText(pianoSong.getSpanString(pianoSong.getString()));
        }
    };


    private void initView(View view){

        sharedPreferences = getActivity().getSharedPreferences("songs",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        map =  sharedPreferences.getAll();


        btn_do=view.findViewById(R.id.doo);
        btn_re=view.findViewById(R.id.re);
        btn_mi=view.findViewById(R.id.mi);
        btn_fa=view.findViewById(R.id.fa);
        btn_so=view.findViewById(R.id.so);
        btn_la=view.findViewById(R.id.la);
        btn_si=view.findViewById(R.id.si);

        btn_re_minus=view.findViewById(R.id.re_minus);
        btn_mi_minus=view.findViewById(R.id.mi_minus);
        btn_fa_minus=view.findViewById(R.id.fa_minus);
        btn_so_minus=view.findViewById(R.id.so_minus);
        btn_la_minus=view.findViewById(R.id.la_minus);
        btn_si_minus=view.findViewById(R.id.si_minus);

        btn_do_plus=view.findViewById(R.id.do_plus);
        btn_re_plus=view.findViewById(R.id.re_plus);
        btn_mi_plus=view.findViewById(R.id.mi_plus);
        btn_fa_plus=view.findViewById(R.id.fa_plus);
        btn_so_plus=view.findViewById(R.id.so_plus);
        btn_la_plus=view.findViewById(R.id.la_plus);
        btn_si_plus=view.findViewById(R.id.si_plus);

        btn_do_plus_plus=view.findViewById(R.id.do_plus_plus);

        btn_cal = view.findViewById(R.id.btn_cal);
        btn_del = view.findViewById(R.id.btn_backspace);
        btn_play = view.findViewById(R.id.btn_play);
        btn_diy = view.findViewById(R.id.btn_diy);
        btn_load = view.findViewById(R.id.load_song);
        btn_save = view.findViewById(R.id.save_song);

        e_input = view.findViewById(R.id.input);
        file_list = view.findViewById(R.id.file_list);

        btn_re_minus.setOnClickListener(pianoOnClickListener);
        btn_mi_minus.setOnClickListener(pianoOnClickListener);
        btn_fa_minus.setOnClickListener(pianoOnClickListener);
        btn_so_minus.setOnClickListener(pianoOnClickListener);
        btn_la_minus.setOnClickListener(pianoOnClickListener);
        btn_si_minus.setOnClickListener(pianoOnClickListener);

        btn_do.setOnClickListener(pianoOnClickListener);
        btn_re.setOnClickListener(pianoOnClickListener);
        btn_mi.setOnClickListener(pianoOnClickListener);
        btn_fa.setOnClickListener(pianoOnClickListener);
        btn_so.setOnClickListener(pianoOnClickListener);
        btn_la.setOnClickListener(pianoOnClickListener);
        btn_si.setOnClickListener(pianoOnClickListener);

        btn_do_plus.setOnClickListener(pianoOnClickListener);
        btn_re_plus.setOnClickListener(pianoOnClickListener);
        btn_mi_plus.setOnClickListener(pianoOnClickListener);
        btn_fa_plus.setOnClickListener(pianoOnClickListener);
        btn_so_plus.setOnClickListener(pianoOnClickListener);
        btn_la_plus.setOnClickListener(pianoOnClickListener);
        btn_si_plus.setOnClickListener(pianoOnClickListener);

        btn_do_plus_plus.setOnClickListener(pianoOnClickListener);

        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment, new CalFragment());
                fragmentTransaction.commit();
            }
        });
        btn_diy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isDiy = !isDiy;
                if (isDiy){
                    Toast.makeText(getActivity(),"谱区模式开启",Toast.LENGTH_SHORT).show();
                    pianoSong = new PianoSong(getActivity());
                } else {
                    Toast.makeText(getActivity(),"谱区模式关闭",Toast.LENGTH_SHORT).show();
                }
            }
        });
        e_input.setMovementMethod(ScrollingMovementMethod.getInstance());
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPlaying = true;
                btn_play.setImageResource(R.mipmap.pause);
                pianoSong.play(pianoPlayer,btn_play);
                btn_play.setImageResource(R.mipmap.play);
                isPlaying = false;
            }
        });
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pianoSong.backspace();
                e_input.setText(pianoSong.getSpanString(pianoSong.getString()));
            }
        });
        btn_load.setOnClickListener(ioOnClickListener);
        btn_save.setOnClickListener(ioOnClickListener);

        file_list.setOnItemClickListener(onItemClickListener);


    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_piano,container,false);
        initView(view);
        pianoPlayer = new PianoPlayer(getActivity());
        pianoSong = new PianoSong(getActivity());
        return view;
    }
}
