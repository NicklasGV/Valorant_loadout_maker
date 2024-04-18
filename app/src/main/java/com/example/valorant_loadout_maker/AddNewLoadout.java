package com.example.valorant_loadout_maker;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import androidx.core.content.ContextCompat;

import com.example.valorant_loadout_maker.Database.LoadoutModel;
import com.example.valorant_loadout_maker.Database.ValoDBHelper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;



public class AddNewLoadout extends BottomSheetDialogFragment{
    public static final String TAG = "ActionBottomDialog";

    private EditText newTaskText;
    private Button newTaskSaveButton;
    private ValoDBHelper db;

    public static AddNewLoadout newInstance() {
        return new AddNewLoadout();
    }

    // onCreates for dialog boxes, like styles and input mode
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_loadout, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newTaskText = getView().findViewById(R.id.newTaskText);
        newTaskSaveButton = getView().findViewById(R.id.newTaskButton);

        db = new ValoDBHelper(getActivity());
        db.openDatabase();

        // Checker for seeing if it's an update or a new task being made
        boolean isUpdate = false;
        final Bundle bundle = getArguments();
        if (bundle != null) {
            isUpdate = true;
            String task = bundle.getString("task");
            newTaskText.setText(task);
            if (task.length() > 0) {
                newTaskSaveButton.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            }
        }
        // Listening for text changes
        newTaskText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            // Visualization for seeing if you can save, by changing save button
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    newTaskSaveButton.setEnabled(false);
                    newTaskSaveButton.setTextColor(Color.GRAY);
                } else {
                    newTaskSaveButton.setEnabled(true);
                    newTaskSaveButton.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Since isUpdate is in another class and we wanna us it in this class, we define it outside.
        boolean finalIsUpdate = isUpdate;
        newTaskSaveButton.setOnClickListener(v -> {
            String text = newTaskText.getText().toString();
            // Here we check for if updating, so either we update or create new
            if (finalIsUpdate) {
                db.updateLoadout(bundle.getInt("id"), text);
            } else {
                LoadoutModel loadout = new LoadoutModel();
                loadout.setLoadout_name(text);
                db.createLoadout(loadout);
            }
            dismiss();
        });
    }

    @Override
    public  void onDismiss(DialogInterface dialog){
        Activity activity = getActivity();
        if (activity instanceof DialogCloseListener){
            ((DialogCloseListener)activity).handleDialogClose(dialog);
        }
    }
}
