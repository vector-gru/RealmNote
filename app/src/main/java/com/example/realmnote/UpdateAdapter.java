package com.example.realmnote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;

import io.realm.Realm;
import io.realm.RealmResults;

public class UpdateAdapter extends RecyclerView.Adapter<UpdateAdapter.MyViewHolder> {

    Context context;
    RealmResults<Note> noteList;

    public UpdateAdapter(Context context, RealmResults<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    } // end constructor

    @NonNull
    @Override
    public UpdateAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UpdateAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.update_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UpdateAdapter.MyViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.updateTitleInput.setText(note.getTitle());
        holder.updateDescriptionOutput.setText(note.getDescription());
       /* String formatedTime = DateFormat.getDateTimeInstance().format(note.getCreatedTime());
        holder.timeOutput.setText(formatedTime);*/

        // we set on onclick listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {


        @Override
        public void onclick(@NonNull MyViewHolder hollder, int postition) {
        Note note = noteList.get(postition);
        hollder.updateTitleInput.setText(note.getTitle());
        hollder.updateDescriptionInput.setText(note.getDescription());
        }
         /*   @Override
            public boolean onClick(View view) {
                PopupMenu menu = new PopupMenu(context, view);
                menu.getMenu().add("Delete");
                //menu.getMenu().add("Edit");

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getTitle().equals("Delete")){
                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();
                            note.deleteFromRealm();
                            realm.commitTransaction();
                            Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show();
                        } // end if
                        return true;
                    }
                });
                menu.show();
                return true;
            }*/



        });



    }



    @Override
    public int getItemCount() {
        return noteList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView updateTitleInput;
        TextView updateDescriptionOutput;
        //TextView timeOutput;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            updateTitleInput = itemView.findViewById(R.id.updateTitleInput);
            updateDescriptionInput = itemView.findViewById(R.id.updateDescriptionInput);
            //timeOutput = itemView.findViewById(R.id.timeoutput);
        }

    } //end MyViewHolder

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
    }*/
}