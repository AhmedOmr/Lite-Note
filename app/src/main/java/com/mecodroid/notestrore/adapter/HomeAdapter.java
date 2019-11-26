package com.mecodroid.notestrore.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mecodroid.Note_Lite.R;
import com.mecodroid.notestrore.data.model.NoteModel;
import com.mecodroid.notestrore.ui.activity.NoteActivity;

import java.text.Bidi;
import java.util.List;

import static com.mecodroid.notestrore.helper.HelperMethod.isRTL;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_EMPTY_LIST_PLACEHOLDER = 0;
    private static final int VIEW_TYPE_OBJECT_VIEW = 1;
    private final Context context;
    private List<NoteModel> notelist;
    private EmptyHolder Emholder;
    private viewholder holder;
    private Typeface cairoregularFont, cairoboldFont, operaslopeFont, sglightFont,
            sgregularFont, cataclysmoFont, bahjFont, proudlyFont, xmyekanFont, tradbdoFont, crosslotsFont;

    public HomeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        initFont();

        if (viewType == VIEW_TYPE_OBJECT_VIEW) {
            View v1 = LayoutInflater.from(context).inflate(R.layout.recycler_row_item, parent, false);
            return new viewholder(v1);
        } else {
            View v2 = LayoutInflater.from(context).inflate(R.layout.recycler_row_item_empty, parent, false);
            return new EmptyHolder(v2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vholder, int postion) {
        switch (getItemViewType(postion)) {
            case VIEW_TYPE_EMPTY_LIST_PLACEHOLDER:
                // return view holder for your placeholder
                Emholder = (EmptyHolder) vholder;
                if (isRTL()) {
                    Emholder.txtEmptyAdapter.setTypeface(cairoboldFont);
                    Emholder.txtEmptyAdapter.setTextSize(20);
                } else {
                    Emholder.txtEmptyAdapter.setTypeface(proudlyFont);
                    Emholder.txtEmptyAdapter.setTextSize(30);

                }
                break;
            case VIEW_TYPE_OBJECT_VIEW:
                // return view holder for your normal list item
                NoteModel dataNote = notelist.get(postion);
                holder = (viewholder) vholder;

                Bidi bidi = new Bidi(notelist.get(postion).getTitle(), Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT);
                Bidi bidi2 = new Bidi(notelist.get(postion).getSubject(), Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT);

                final int sdk = Build.VERSION.SDK_INT;
                if (sdk < Build.VERSION_CODES.JELLY_BEAN) {
                    holder.recyclerHomeRowItemRlContainer.
                            setBackgroundDrawable(ContextCompat.getDrawable(context, dataNote.getPrcolor()));
                } else {
                    holder.recyclerHomeRowItemRlContainer.
                            setBackground(ContextCompat.getDrawable(context, dataNote.getPrcolor()));
                }
                if (bidi.getBaseLevel() == 0) {
                    holder.recyclerHomeRowItemTxtTitle.setGravity(Gravity.START);
                    holder.recyclerHomeRowItemTxtTitle.setTypeface(tradbdoFont);
                    holder.recyclerHomeRowItemTxtTitle.setTextSize(20);

                } else {
                    holder.recyclerHomeRowItemTxtTitle.setGravity(Gravity.START);
                    holder.recyclerHomeRowItemTxtTitle.setTextSize(18);
                    holder.recyclerHomeRowItemTxtTitle.setTypeface(cairoboldFont);

                }
                if (bidi2.getBaseLevel() == 0) {
                    holder.recyclerHomeRowItemTxtsSubject.setGravity(Gravity.START);
                    holder.recyclerHomeRowItemTxtsSubject.setTypeface(sgregularFont);


                } else {
                    holder.recyclerHomeRowItemTxtsSubject.setGravity(Gravity.START);
                    holder.recyclerHomeRowItemTxtsSubject.setTypeface(cairoregularFont);
                }

                holder.recyclerHomeRowItemTxtTitle.setText(dataNote.getTitle());
                holder.recyclerHomeRowItemTxtsSubject.setText(dataNote.getSubject());
                if (bidi2.getBaseLevel() == 0 || bidi.getBaseLevel() == 0) {
                    holder.recyclerHomeRowItemTxtDate.setText(dataNote.getShowdateEn());
                    holder.recyclerHomeRowItemTxtDate.setTypeface(operaslopeFont);
                    holder.recyclerHomeRowItemTxtDate.setGravity(Gravity.START);


                } else {
                    holder.recyclerHomeRowItemTxtDate.setText(dataNote.getShowdateAr());
                    holder.recyclerHomeRowItemTxtDate.setTypeface(cairoboldFont);
                    holder.recyclerHomeRowItemTxtDate.setGravity(Gravity.START);
                }

                break;
        }
        /* This class has getBaseLevel() method
         which returns 0 if your text is left-to-right otherwise 1 (if right-to-left).*/

    }

    @Override
    public int getItemCount() {
        return (notelist == null || notelist.isEmpty()) ? 1 : notelist.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (notelist == null) {
            return VIEW_TYPE_EMPTY_LIST_PLACEHOLDER;
        } else {
            return VIEW_TYPE_OBJECT_VIEW;
        }
    }

    public void setDatanote(List<NoteModel> noteList) {
        this.notelist = noteList;

    }

    public void initFont() {
        tradbdoFont = ResourcesCompat.getFont(context, R.font.tradbdo);
        crosslotsFont = ResourcesCompat.getFont(context, R.font.croos_lots_offer);
        cairoboldFont = ResourcesCompat.getFont(context, R.font.cairo_bold);
        operaslopeFont = ResourcesCompat.getFont(context, R.font.slope_opera);
        cataclysmoFont = ResourcesCompat.getFont(context, R.font.cataclysmo);
        bahjFont = ResourcesCompat.getFont(context, R.font.bahij_insan);
        proudlyFont = ResourcesCompat.getFont(context, R.font.proudlybold);
        xmyekanFont = ResourcesCompat.getFont(context, R.font.xm_yekan_bold);
        sglightFont = ResourcesCompat.getFont(context, R.font.sg_kara_light);
        sgregularFont = ResourcesCompat.getFont(context, R.font.sg_kara_regular);
    }

    class viewholder extends RecyclerView.ViewHolder {
        TextView recyclerHomeRowItemTxtTitle, recyclerHomeRowItemTxtsSubject, recyclerHomeRowItemTxtDate;
        RelativeLayout recyclerHomeRowItemRlContainer;

        private viewholder(View itemView) {
            super(itemView);
            recyclerHomeRowItemTxtTitle = itemView.findViewById(R.id.recycler_home_row_item_txt_title);
            recyclerHomeRowItemTxtsSubject = itemView.findViewById(R.id.recycler_home_row_item_txt_sub);
            recyclerHomeRowItemTxtDate = itemView.findViewById(R.id.recycler_home_row_item_txt_date);
            recyclerHomeRowItemRlContainer = itemView.findViewById(R.id.recycler_row_item_rl_container);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendnote = new Intent(context, NoteActivity.class);
                    sendnote.putExtra("note", notelist.get(getAdapterPosition()));
                    sendnote.putExtra("prcolor", notelist.get(getAdapterPosition()).getPrcolor());
                    context.startActivity(sendnote);
                }
            });
        }
    }

    class EmptyHolder extends RecyclerView.ViewHolder {
        TextView txtEmptyAdapter;

        private EmptyHolder(@NonNull View itemView) {
            super(itemView);
            txtEmptyAdapter = itemView.findViewById(R.id.recycler_empty_item_txt);
        }
    }
}
