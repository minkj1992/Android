package com.example.mydraw;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mydraw.R;

public class WordsFragment extends Fragment {
    public interface OnWordSelectedListener {
        public void onWordSelected(int position);
    }
    OnWordSelectedListener mWordSelListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_word, container, false);
        ListView listView = view.findViewById(R.id.word_list);
        listView.setAdapter(
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                        Data.words)
        );
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (mWordSelListener != null)
                            mWordSelListener.onWordSelected(position);
                    }
                });
        mWordSelListener = (OnWordSelectedListener) getActivity();
        return view;
    }
}