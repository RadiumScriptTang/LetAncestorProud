package com.example.radiumscripttang.software_engineer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class PianoFragment extends Fragment {
    ImageButton btn_cal;

    ImageButton btn_re_minus, btn_mi_minus, btn_fa_minus, btn_so_minus, btn_la_minus, btn_si_minus,
            btn_do,btn_re,btn_mi,btn_fa,btn_so,btn_la,btn_si,btn_do_plus,btn_re_plus,btn_mi_plus,btn_fa_plus,btn_so_plus,btn_la_plus,btn_si_plus,
            btn_do_plus_plus;
    PianoPlayer pianoPlayer;

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
        }
    };


    private void initView(View view){
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

        btn_re_minus.setOnClickListener(pianoOnClickListener);
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
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_piano,container,false);
        initView(view);
        pianoPlayer = new PianoPlayer(getActivity());
        return view;
    }
}
