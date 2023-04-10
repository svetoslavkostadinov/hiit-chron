package com.skconsultllc.hcr.adapters;

import static android.provider.Settings.System.getString;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.skconsultllc.hcr.R;
import com.skconsultllc.hcr.models.HiitAction;

import java.util.List;

public class WorkoutStageListAdapter extends ArrayAdapter<HiitAction> {

    public WorkoutStageListAdapter(@NonNull Context context, @NonNull List<HiitAction> objects) {
        super(context, 0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.workout_stages_list_item_layout, parent, false);
        }

        HiitAction item = getItem(position);

        TextView titleTextView = convertView.findViewById(R.id.workoutTypeTextView);
        TextView descriptionTextView = convertView.findViewById(R.id.workoutDurationTextView);

        titleTextView.setText(item.getHiitActionType().toString());
        descriptionTextView.setText(item.getDurationStringValue());

        return convertView;
    }

}
