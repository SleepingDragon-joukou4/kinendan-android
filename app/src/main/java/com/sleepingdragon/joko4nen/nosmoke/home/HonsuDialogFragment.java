package com.sleepingdragon.joko4nen.nosmoke.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;


import com.sleepingdragon.joko4nen.nosmoke.R;
import com.sleepingdragon.joko4nen.nosmoke.util.network.SmokingUpsertConnectionService;

import org.apache.commons.lang3.StringUtils;

import butterknife.InjectView;

/**
 * Created by ryu on 15/07/01.
 */
public class HonsuDialogFragment extends DialogFragment {
    SmokingUpsertConnectionService service;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        final HomeActivity activity = (HomeActivity) getActivity();
        final String mhousu = activity.mhousu.getText().toString();

        View content = inflater.inflate(R.layout.honsu_dialog_fragment, null);

        final NumberPicker numberPicker = (NumberPicker) content.findViewById(R.id.honsuNumberPicker);
        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(0);

        if (StringUtils.isNotEmpty(mhousu)) {
            numberPicker.setValue(Integer.parseInt(mhousu));
        }

        builder.setView(content);

        builder.setTitle("本数入力")
                .setMessage("喫煙本数を入力してください。")
                .setPositiveButton("キャンセル", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                })
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("dialog","OKClick");

                        Integer value = numberPicker.getValue();

                        SharedPreferences sPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
                        String userID = sPreferences.getString("UserID", "なし");

                        service = new SmokingUpsertConnectionService(userID,"",value.toString(),mhousu);
                        service.upsert();

                        activity.onSetHonsu(value.toString());

                    }
                });

        return builder.create();
    }
}
