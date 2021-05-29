package com.jstechnologies.internshalanotesapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jstechnologies.internshalanotesapp.R;
import com.jstechnologies.internshalanotesapp.data.models.Note;
import com.jstechnologies.internshalanotesapp.utils.AnimUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesHolder> {

    List<Note> models;
    OnNoteItemClick onNoteItemClick;

    public NotesAdapter() {
        models=new ArrayList<>();
    }

    public void setModels(List<Note> models) {
        this.models = models;
        notifyDataSetChanged();
    }

    public void setOnNoteItemClickListener(OnNoteItemClick onNoteItemClick) {
        this.onNoteItemClick = onNoteItemClick;
    }

    @NonNull
    @Override
    public NotesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesHolder holder, int position) {
        holder.bind(models.get(position));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    protected class NotesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title,content,date,delete,edit;
        ImageView expand,important;
        LinearLayout expandLayout;
        boolean isExpanded=false;

        public NotesHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            content=itemView.findViewById(R.id.content);
            important=itemView.findViewById(R.id.important);
            expand=itemView.findViewById(R.id.expand);
            date=itemView.findViewById(R.id.datetime);
            expandLayout=itemView.findViewById(R.id.expandLayout);
            delete=itemView.findViewById(R.id.delete);
            edit=itemView.findViewById(R.id.edit);

        }
        void bind(Note note){

            int resId=note.isImportant()?R.drawable.ic_star_filled:R.drawable.ic_star;
            important.setImageDrawable(itemView.getContext().getResources().getDrawable(resId));
            title.setText(note.getTitle());
            content.setText(note.getNotecontent());
            date.setText(new SimpleDateFormat("hh:mm a, dd MMM yyyy").format(new Date(note.getDateTime())));
            expand.setOnClickListener(this::onClick);
            delete.setOnClickListener(this::onClick);
            edit.setOnClickListener(this::onClick);
        }
        void toggleExpandCollapse(){
            if(isExpanded)
            {
                AnimUtils.collapse(expandLayout);
                expand.animate().rotation(0f).start();
                isExpanded=false;
            }
            else{
                AnimUtils.expand(expandLayout);
                expand.animate().rotation(180f).start();
                isExpanded=true;
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.expand:toggleExpandCollapse();break;
                case R.id.delete:if(onNoteItemClick!=null)onNoteItemClick.onDeleteClick(models.get(getAdapterPosition()).getId());break;
                case R.id.edit:if(onNoteItemClick!=null)onNoteItemClick.onEditClick(models.get(getAdapterPosition()));break;
            }
        }
    }

    public interface OnNoteItemClick{
        void onDeleteClick(int id);
        void onEditClick(Note note);
    }
}
