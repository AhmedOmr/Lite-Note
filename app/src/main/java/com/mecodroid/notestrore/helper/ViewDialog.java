package com.mecodroid.notestrore.helper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.mecodroid.Note_Lite.R;
import com.mecodroid.notestrore.data.local.NoteHelperDb;
import com.mecodroid.notestrore.data.model.NoteModel;
import com.mecodroid.notestrore.ui.fragment.homeCycle.HomeFragment;

import static com.mecodroid.notestrore.data.local.NoteHelperDb.KEY_ID;
import static com.mecodroid.notestrore.data.local.NoteHelperDb.TABLE_NOTES;


public class ViewDialog {

    NoteHelperDb helper;
    SQLiteDatabase db;
    //private ClientApiServer apiServices;
    private Activity activity;
    private Intent intentHome;
    private NoteModel noteModel;

    public void showDialog(final Activity activity, String massage) {
        intentHome = activity.getIntent();
        noteModel = intentHome.getExtras().getParcelable("note");
        helper = new NoteHelperDb(activity);
        db = helper.getWritableDatabase();
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.shape_dialog_layout);
        dialog.setCanceledOnTouchOutside(true);
        TextView text = dialog.findViewById(R.id.text);
        text.setText(massage);
        Button dialogButtonOk = dialog.findViewById(R.id.dialog_ok);
        dialogButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = helper.getWritableDatabase();
                int deleteRow = db.delete(TABLE_NOTES, KEY_ID + "=?",
                        new String[]{String.valueOf(noteModel.getId())});
                if (deleteRow > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        new HomeFragment().viewNotes();
                    }
                    activity.finish();
                }

            }
        });
        dialog.setCanceledOnTouchOutside(true);
        Button dialogButtonNo = dialog.findViewById(R.id.dialog_no);
        dialogButtonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });


        dialog.show();

    }
}
