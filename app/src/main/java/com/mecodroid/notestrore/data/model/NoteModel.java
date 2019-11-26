package com.mecodroid.notestrore.data.model;

import android.os.Parcel;
import android.os.Parcelable;


public class NoteModel implements Parcelable {

    public static final Creator<NoteModel> CREATOR = new Creator<NoteModel>() {
        @Override
        public NoteModel createFromParcel(Parcel in) {
            return new NoteModel(in);
        }

        @Override
        public NoteModel[] newArray(int size) {
            return new NoteModel[size];
        }
    };
    private int id;
    private String title;
    private String subject;
    private String date;
    private String ShowdateEn;
    private String ShowdateAr;
    private int Prcolor;


    public NoteModel(int id, String title, String subject, String date,
                     String showdateEn, String showdateAr, int prcolor) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.date = date;
        ShowdateEn = showdateEn;
        ShowdateAr = showdateAr;
        Prcolor = prcolor;
    }

    public NoteModel(int id, String title, String subject, String date) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.date = date;
    }

    private NoteModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        subject = in.readString();
    }

    public NoteModel(int id, String title, String subject) {
        this.id = id;
        this.title = title;
        this.subject = subject;
    }

    public int getPrcolor() {
        return Prcolor;
    }

    public void setPrcolor(int prcolor) {
        Prcolor = prcolor;
    }

    public String getShowdateAr() {
        return ShowdateAr;
    }

    public void setShowdateAr(String showdateAr) {
        ShowdateAr = showdateAr;
    }

    public String getShowdateEn() {
        return ShowdateEn;
    }

    public void setShowdateEn(String showdateEn) {
        ShowdateEn = showdateEn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(subject);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
