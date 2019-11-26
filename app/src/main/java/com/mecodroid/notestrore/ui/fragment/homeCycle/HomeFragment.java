package com.mecodroid.notestrore.ui.fragment.homeCycle;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaeger.library.StatusBarUtil;
import com.mecodroid.Note_Lite.R;
import com.mecodroid.notestrore.adapter.HomeAdapter;
import com.mecodroid.notestrore.data.local.NoteHelperDb;
import com.mecodroid.notestrore.data.model.NoteModel;
import com.mecodroid.notestrore.ui.activity.NoteActivity;
import com.mecodroid.notestrore.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.mecodroid.notestrore.data.local.NoteHelperDb.KEY_ARDATE;
import static com.mecodroid.notestrore.data.local.NoteHelperDb.KEY_COLOR;
import static com.mecodroid.notestrore.data.local.NoteHelperDb.KEY_DEFDATE;
import static com.mecodroid.notestrore.data.local.NoteHelperDb.KEY_ENDATE;
import static com.mecodroid.notestrore.data.local.NoteHelperDb.KEY_ID;
import static com.mecodroid.notestrore.data.local.NoteHelperDb.KEY_SUBJECT;
import static com.mecodroid.notestrore.data.local.NoteHelperDb.KEY_TITLE;
import static com.mecodroid.notestrore.data.local.NoteHelperDb.TABLE_NOTES;
import static com.mecodroid.notestrore.helper.HelperMethod.customMassageError;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.home_fragment_recycler_view_notes)
    RecyclerView homeFragmentRecyclerViewNotes;
    @BindView(R.id.home_fragment_btn_float_add)
    FloatingActionButton homeFragmentBtnFloatAdd;
    Unbinder unbinder;
    HomeAdapter adapter;
    NoteHelperDb helper;
    SQLiteDatabase db;
    List<NoteModel> noteList;

    public HomeFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initFragment();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        initTools();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void initTools() {
        StatusBarUtil.setTransparent(getActivity());
        helper = new NoteHelperDb(getActivity());
        db = helper.getWritableDatabase();
        setuprecyclerview();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setuprecyclerview() {
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        homeFragmentRecyclerViewNotes.setLayoutManager(lm);
        adapter = new HomeAdapter(getActivity());
        homeFragmentRecyclerViewNotes.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void viewNotes() {
        db = helper.getWritableDatabase();
        noteList = new ArrayList<>();
        try {
            Cursor cursor =
                    db.query(TABLE_NOTES, new String[]{KEY_ID, KEY_TITLE, KEY_SUBJECT, KEY_DEFDATE,
                                    KEY_ENDATE, KEY_ARDATE, KEY_COLOR},
                            null, null, null, null,
                            KEY_DEFDATE + " desc ");
            if (cursor.moveToNext()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                    String noteTitle = cursor.getString(cursor.getColumnIndex(KEY_TITLE));
                    String noteSubject = cursor.getString(cursor.getColumnIndex(KEY_SUBJECT));
                    String noteDate = cursor.getString(cursor.getColumnIndex(KEY_DEFDATE));
                    String noteEnDate = cursor.getString(cursor.getColumnIndex(KEY_ENDATE));
                    String noteArDate = cursor.getString(cursor.getColumnIndex(KEY_ARDATE));
                    int coulm = cursor.getInt(cursor.getColumnIndex(KEY_COLOR));

                    noteList.add(new NoteModel(id, noteTitle, noteSubject, noteDate,
                            noteEnDate, noteArDate, coulm));
                } while (cursor.moveToNext());
                adapter.setDatanote(noteList);
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            customMassageError(getActivity(), e.getMessage());
        }
    }

    @OnClick(R.id.home_fragment_btn_float_add)
    public void onViewClicked() {
        Intent intentNote = new Intent(getActivity(), NoteActivity.class);
        startActivity(intentNote);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onStart() {
        super.onStart();
        viewNotes();
    }

    @Override
    public void onBack() {
        getActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
