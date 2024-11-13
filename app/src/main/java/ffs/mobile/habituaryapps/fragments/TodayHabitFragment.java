package com.example.habbit.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.habbit.R;

public class TodayHabitFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today_habit, container, false);

        CheckBox habitCheckBox = view.findViewById(R.id.habit_checkbox);
        habitCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Action when checked
            } else {
                // Action when unchecked
            }
        });

        return view;
    }
}
