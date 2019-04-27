package com.example.mydraw;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements WordsFragment.OnWordSelectedListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            if (findViewById(R.id.fragment_container) != null) {
                if (savedInstanceState != null) {
                    return;
                }
                WordsFragment wordsFragment = new WordsFragment();
                FragmentManager fm = getSupportFragmentManager(); // replace getFragmentManager()
                fm.beginTransaction().add(R.id.fragment_container, wordsFragment).commit();
            }
        }
        public void onWordSelected(int position) {
            if (findViewById(R.id.fragment_container) == null) {
                DefinitionFragment defFrag = (DefinitionFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.definition_fragment);
                defFrag.updateDefinitionView(position);
            } else {
                DefinitionFragment newFragment = new DefinitionFragment();
                Bundle args = new Bundle();
                args.putInt(DefinitionFragment.ARG_POSITION, position);
                newFragment.setArguments(args);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }
    }


//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//
//        Fragment1 frag1 = new Fragment1();
//        FragmentTransaction fmtrans = getSupportFragmentManager().beginTransaction();
//        fmtrans.add(R.id.fragment_container, frag1);
//        fmtrans.commit();
//
//        btn1 = findViewById(R.id.button);
//        btn1.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                Fragment1 frag1 = new Fragment1();
//                FragmentTransaction fmtrans = getSupportFragmentManager().beginTransaction();
//                fmtrans.replace(R.id.fragment_container, frag1);
//                fmtrans.addToBackStack(null);
//                fmtrans.commit();
//            }
//        });
//
//        btn2 = findViewById(R.id.button2);
//        btn2.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                Fragment2 frag2 = new Fragment2();
//                FragmentTransaction fmtrans = getSupportFragmentManager().beginTransaction();
//                fmtrans.replace(R.id.fragment_container, frag2);
//                fmtrans.addToBackStack(null);
//                fmtrans.commit();
//            }
//        });
//    }
//}
