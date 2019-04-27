package com.example.mydraw;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mydraw.R;

public class DefinitionFragment extends Fragment {
    final static String ARG_POSITION = "position";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_def, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            int pos = args.getInt(ARG_POSITION);
            updateDefinitionView(pos);
        }
    }
    public void updateDefinitionView(int position) {
        TextView def = (TextView) getView().findViewById(R.id.definition);
        def.setText(Data.definitions[position]);
    }
}