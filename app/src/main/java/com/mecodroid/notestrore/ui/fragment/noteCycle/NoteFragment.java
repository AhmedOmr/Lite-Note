package com.mecodroid.notestrore.ui.fragment.noteCycle;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.mecodroid.Note_Lite.R;
import com.mecodroid.notestrore.data.local.NoteHelperDb;
import com.mecodroid.notestrore.data.model.NoteModel;
import com.mecodroid.notestrore.helper.ViewDialog;
import com.mecodroid.notestrore.ui.fragment.BaseFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
import static com.mecodroid.notestrore.helper.HelperMethod.customMassageDone;
import static com.mecodroid.notestrore.helper.HelperMethod.customMassageError;
import static com.mecodroid.notestrore.helper.HelperMethod.disappearKeypad;
import static com.mecodroid.notestrore.helper.HelperMethod.isRTL;
import static com.mecodroid.notestrore.helper.NoteLiteConstants.CONTENT_NOTE_SIZE;
import static com.mecodroid.notestrore.helper.SharedPreferencesManger.LoadIntegerData;
import static com.mecodroid.notestrore.helper.SharedPreferencesManger.SaveData;
import static com.mecodroid.notestrore.helper.Vaildation.isValidContent;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoteFragment extends BaseFragment implements View.OnTouchListener {

    @BindView(R.id.note_fragment_img_save)
    ImageView noteFragmentImgSave;
    @BindView(R.id.note_fragment_img_update)
    ImageView noteFragmentImgUpdate;
    @BindView(R.id.note_fragment_img_format)
    ImageView noteFragmentImgFormat;
    @BindView(R.id.note_fragment_img_delet)
    ImageView noteFragmentImgDelet;
    @BindView(R.id.note_fragment_img_cancel)
    ImageView noteFragmentImgCancel;
    @BindView(R.id.note_fragment_lin_options)
    LinearLayout noteFragmentLinOptions;
    @BindView(R.id.note_fragment_et_note_title)
    EditText noteFragmentEtNoteTitle;
    @BindView(R.id.note_fragment_rl_note)
    RelativeLayout noteFragmentRlNote;
    @BindView(R.id.note_fragment_et_note_content)
    EditText noteFragmentEtNoteContent;
    @BindView(R.id.note_fragment_scroll)
    ScrollView noteFragmentScroll;
    @BindView(R.id.note_fragment_txt_tit_gravity)
    TextView noteFragmentTxtTitGravity;
    @BindView(R.id.note_fragment_img_tit_lfgravity)
    ImageView noteFragmentImgTitLfgravity;
    @BindView(R.id.note_fragment_img_tit_cengravity)
    ImageView noteFragmentImgTitCengravity;
    @BindView(R.id.note_fragment_img_tit_rtgravity)
    ImageView noteFragmentImgTitRtgravity;
    @BindView(R.id.note_fragment_rl_title)
    RelativeLayout noteFragmentRlTitle;
    @BindView(R.id.note_fragment_txt_sub_gravity)
    TextView noteFragmentTxtSubGravity;
    @BindView(R.id.note_fragment_img_sub_lfgravity)
    ImageView noteFragmentImgSubLfgravity;
    @BindView(R.id.note_fragment_img_sub_cengravity)
    ImageView noteFragmentImgSubCengravity;
    @BindView(R.id.note_fragment_img_sub_rtgravity)
    ImageView noteFragmentImgSubRtgravity;
    @BindView(R.id.note_fragment_rl_content)
    RelativeLayout noteFragmentRlContent;
    @BindView(R.id.note_formt_rl_container)
    RelativeLayout noteFormtRlContainer;
    @BindView(R.id.note_fragment_rl_options)
    RelativeLayout noteFragmentRlOptions;
    Unbinder unbinder;
    @BindView(R.id.note_fragment_txt_subsize)
    TextView noteFragmentTxtSubsize;
    @BindView(R.id.note_fragment_seekbar)
    SeekBar noteFragmentSeekbar;
    @BindView(R.id.note_fragment_rl_sub_size)
    RelativeLayout noteFragmentRlSubSize;
    NoteHelperDb helper;
    SQLiteDatabase db;
    NoteModel noteModel;
    String noteTitle, noteSubject;
    Intent intent;
    RadioButton rdBtnNegPriority, rdBtnLowPriority, rdBtnMedPriority, rdBtnHighPriority;
    int scolor;
    Button btnPriority;
    private Dialog builder;
    private View view;
    private String dateFormat, dateFormatEn, dateFormatAr;
    private Intent intentHome;
    private Typeface cairoboldFont, proudlyFont;
    private TextView txtPriority;

    public NoteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initFragment();
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_note, container, false);
        unbinder = ButterKnife.bind(this, view);
        initTools();
        return view;
    }

    // annotation to remove warning with on touch listener
    @SuppressLint("ClickableViewAccessibility")
    public void initTools() {
        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.dark_blue));
        cairoboldFont = ResourcesCompat.getFont(getActivity(), R.font.cairo_bold);
        proudlyFont = ResourcesCompat.getFont(getActivity(), R.font.proudlybold);
        disappearKeypad(getActivity(), getView());
        helper = new NoteHelperDb(getActivity());
        db = helper.getWritableDatabase();
        setDailogdesign();
        scolor = selected();
        dateFormat = new SimpleDateFormat("YYYY-MM-d HH:mm:ss.sss",
                Locale.ENGLISH).format(new Date());
        dateFormatEn = new SimpleDateFormat(" hh:mm a .. d-MM-YYYY  ",
                Locale.ENGLISH).format(new Date());
        dateFormatAr = new SimpleDateFormat(" hh:mm a .. d-MM-YYYY ",
                new Locale("ar")).format(new Date());
        intentHome = getActivity().getIntent();
        if (intentHome != null && intentHome.hasExtra("note")) {
            noteFragmentImgDelet.setVisibility(View.VISIBLE);
            noteFragmentImgUpdate.setVisibility(View.VISIBLE);
            try {
                noteFragmentImgSave.setVisibility(View.GONE);
                noteModel = intentHome.getExtras().getParcelable("note");
                noteFragmentEtNoteTitle.setText(noteModel.getTitle());
                noteFragmentEtNoteContent.setText(noteModel.getSubject());
            } catch (Exception e) {
                customMassageError(getActivity(), e.getMessage());
            }
        } else {
            noteFragmentImgDelet.setVisibility(View.GONE);
            noteFragmentImgUpdate.setVisibility(View.GONE);
        }
        noteFragmentEtNoteContent.setOnTouchListener(this);
        noteFragmentEtNoteTitle.setOnTouchListener(this);


        seekBarTxtSize();
        int sizeContent = LoadIntegerData(getActivity(), CONTENT_NOTE_SIZE);
        if (sizeContent != 0) {
            noteFragmentEtNoteContent.setTextSize(sizeContent);
            noteFragmentSeekbar.setProgress(sizeContent);
        } else {
            noteFragmentEtNoteContent.setTextSize(18);
            noteFragmentSeekbar.setProgress(18);

        }
    }

    public void saveNote() {
        noteTitle = noteFragmentEtNoteTitle.getText().toString();
        noteSubject = noteFragmentEtNoteContent.getText().toString();
        intent = getActivity().getIntent();
        if (isValidContent(noteTitle) || isValidContent(noteSubject)) {
            setDailogdesign();
            builder.show();
            btnPriority.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scolor = selected();
                    builder.dismiss();
                    ContentValues values = new ContentValues();

                    db = helper.getWritableDatabase();
                    values.put(KEY_TITLE, noteTitle);
                    values.put(KEY_SUBJECT, noteSubject);
                    values.put(KEY_DEFDATE, dateFormat);
                    values.put(KEY_ENDATE, dateFormatEn);
                    values.put(KEY_ARDATE, dateFormatAr);
                    values.put(KEY_COLOR, scolor);

                    long saveRow = db.insert(TABLE_NOTES, null, values);
                    if (saveRow > 0) {
                        customMassageDone(getActivity(), getResources().getString(R.string.note_saved));
                        noteFragmentEtNoteTitle.setText("");
                        noteFragmentEtNoteContent.setText("");
                        getActivity().finish();
                    }
                }
            });
        } else {
            customMassageError(getActivity(), getResources().getString(R.string.write_title_sub));
        }
    }

    public void updateNote() {
        db = helper.getWritableDatabase();
        noteTitle = noteFragmentEtNoteTitle.getText().toString();
        noteSubject = noteFragmentEtNoteContent.getText().toString();
        intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra("note")) {
            if (isValidContent(noteTitle) || isValidContent(noteSubject)) {
                setDailogdesign();
                builder.show();
                if (intent.hasExtra("prcolor")) {
                    int prcolor = intent.getExtras().getInt("prcolor");
                    if (prcolor == R.drawable.shape_priority_neg) {
                        rdBtnNegPriority.setChecked(true);
                    } else if (prcolor == R.drawable.shape_priority_low) {
                        rdBtnLowPriority.setChecked(true);
                    } else if (prcolor == R.drawable.shape_priority_med) {
                        rdBtnMedPriority.setChecked(true);
                    } else if (prcolor == R.drawable.shape_priority_high) {
                        rdBtnHighPriority.setChecked(true);
                    }
                } else {
                    rdBtnNegPriority.setChecked(true);

                }

                btnPriority.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scolor = selected();
                        builder.dismiss();
                        ContentValues values = new ContentValues();
                        db = helper.getWritableDatabase();
                        values.put(KEY_TITLE, noteTitle);
                        values.put(KEY_SUBJECT, noteSubject);
                        values.put(KEY_DEFDATE, dateFormat);
                        values.put(KEY_ENDATE, dateFormatEn);
                        values.put(KEY_ARDATE, dateFormatAr);
                        values.put(KEY_COLOR, scolor);

                        int updateRow = db.update(TABLE_NOTES, values, KEY_ID + "=?",
                                new String[]{String.valueOf(noteModel.getId())});
                        if (updateRow > 0) {
                            customMassageDone(getActivity(), getResources().getString(R.string.note_updated));
                            getActivity().finish();
                        }
                    }
                });
            } else {
                customMassageError(getActivity(), getResources().getString(R.string.write_title_sub));
            }
        }
    }

    public void deleteNote() {

        ViewDialog viewDialog = new ViewDialog();
        viewDialog.showDialog(getActivity(), getResources().getString(R.string.massage_delete));
    }

    public void canceleNote() {
        getActivity().finish();
    }

    public void setDailogdesign() {
        builder = new Dialog(getActivity());
        View vw = LayoutInflater.from(getActivity()).inflate(R.layout.shape_dialog_priority_layout,
                (ViewGroup) view.findViewById(R.id.scroll_priority_container));
        txtPriority = vw.findViewById(R.id.txt_priority);
        rdBtnNegPriority = vw.findViewById(R.id.rd_neg_priority);
        rdBtnLowPriority = vw.findViewById(R.id.rd_low_priority);
        rdBtnMedPriority = vw.findViewById(R.id.rd_med_priority);
        rdBtnHighPriority = vw.findViewById(R.id.rd_high_priority);
        btnPriority = vw.findViewById(R.id.btn_priority);
        if (vw.getParent() != null) {
            ((ViewGroup) vw.getParent()).removeView(vw);
        }
        builder.setContentView(vw, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        if (isRTL()) {
            txtPriority.setTypeface(cairoboldFont);
            txtPriority.setTextSize(28);
            rdBtnNegPriority.setTypeface(cairoboldFont);
            rdBtnNegPriority.setTextSize(20);
            rdBtnLowPriority.setTypeface(cairoboldFont);
            rdBtnLowPriority.setTextSize(20);
            rdBtnMedPriority.setTypeface(cairoboldFont);
            rdBtnMedPriority.setTextSize(20);
            rdBtnHighPriority.setTypeface(cairoboldFont);
            rdBtnHighPriority.setTextSize(20);
            btnPriority.setTypeface(cairoboldFont);
            btnPriority.setTextSize(20);

        } else {
            txtPriority.setTypeface(proudlyFont);
            txtPriority.setTextSize(40);
            rdBtnNegPriority.setTypeface(proudlyFont);
            rdBtnNegPriority.setTextSize(30);
            rdBtnLowPriority.setTypeface(proudlyFont);
            rdBtnLowPriority.setTextSize(30);
            rdBtnMedPriority.setTypeface(proudlyFont);
            rdBtnMedPriority.setTextSize(30);
            rdBtnHighPriority.setTypeface(proudlyFont);
            rdBtnHighPriority.setTextSize(30);
            btnPriority.setTypeface(proudlyFont);
            btnPriority.setTextSize(26);
        }
        builder.setCancelable(true);
    }

    public int selected() {
        int notePriority = R.drawable.shape_priority_neg;
        if (rdBtnNegPriority.isChecked()) {
            notePriority = R.drawable.shape_priority_neg;
        } else if (rdBtnLowPriority.isChecked()) {
            notePriority = R.drawable.shape_priority_low;
        } else if (rdBtnMedPriority.isChecked()) {
            notePriority = R.drawable.shape_priority_med;
        } else if (rdBtnHighPriority.isChecked()) {
            notePriority = R.drawable.shape_priority_high;
        }
        return notePriority;
    }

    private void seekBarTxtSize() {
        noteFragmentSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pval = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
                SaveData(getActivity(), CONTENT_NOTE_SIZE, pval);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                noteFragmentEtNoteContent.setTextSize(pval);
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.note_fragment_et_note_title:
            case R.id.note_fragment_et_note_content:
                noteFormtRlContainer.setVisibility(View.GONE);
                noteFragmentImgCancel.setVisibility(View.VISIBLE);
                noteFragmentImgFormat.setVisibility(View.VISIBLE);
                if (intentHome != null && intentHome.hasExtra("note")) {
                    noteFragmentImgDelet.setVisibility(View.VISIBLE);
                    noteFragmentImgUpdate.setVisibility(View.VISIBLE);
                } else {
                    noteFragmentImgSave.setVisibility(View.VISIBLE);
                }
                break;
        }
        return false;
    }

    @OnClick({R.id.note_fragment_img_save, R.id.note_fragment_img_update,
            R.id.note_fragment_img_format, R.id.note_fragment_img_delet,
            R.id.note_fragment_img_cancel,
            R.id.note_fragment_img_tit_lfgravity, R.id.note_fragment_img_tit_cengravity,
            R.id.note_fragment_img_tit_rtgravity, R.id.note_fragment_img_sub_lfgravity,
            R.id.note_fragment_img_sub_cengravity, R.id.note_fragment_img_sub_rtgravity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.note_fragment_img_save:
                saveNote();
                break;
            case R.id.note_fragment_img_update:
                updateNote();
                break;
            case R.id.note_fragment_img_format:
                noteFormtRlContainer.setVisibility(View.VISIBLE);
                noteFragmentImgCancel.setVisibility(View.GONE);
                noteFragmentImgFormat.setVisibility(View.GONE);
                if (intentHome != null && intentHome.hasExtra("note")) {
                    noteFragmentImgDelet.setVisibility(View.GONE);
                    noteFragmentImgUpdate.setVisibility(View.GONE);

                } else {
                    noteFragmentImgSave.setVisibility(View.GONE);
                }
                break;
            case R.id.note_fragment_img_delet:
                deleteNote();
                break;
            case R.id.note_fragment_img_cancel:
                canceleNote();
                disappearKeypad(getActivity(), noteFragmentImgCancel);
                break;
            case R.id.note_fragment_img_tit_lfgravity:
                noteFragmentEtNoteTitle.setGravity(Gravity.START);
                break;
            case R.id.note_fragment_img_tit_cengravity:
                noteFragmentEtNoteTitle.setGravity(Gravity.CENTER);
                break;
            case R.id.note_fragment_img_tit_rtgravity:
                noteFragmentEtNoteTitle.setGravity(Gravity.END);
                break;
            case R.id.note_fragment_img_sub_lfgravity:
                noteFragmentEtNoteContent.setGravity(Gravity.START);
                break;
            case R.id.note_fragment_img_sub_cengravity:
                noteFragmentEtNoteContent.setGravity(Gravity.CENTER);
                break;
            case R.id.note_fragment_img_sub_rtgravity:
                noteFragmentEtNoteContent.setGravity(Gravity.END);
                break;
        }
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
