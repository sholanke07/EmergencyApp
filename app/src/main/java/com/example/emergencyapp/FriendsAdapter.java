package com.example.emergencyapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder> {

        private static final Object MODE_PRIVATE = 1;
        private Context context;
        private List<Friends> friendsList;


    public FriendsAdapter(Context context, List<Friends> friendsList) {
            this.context = context;
            this.friendsList = friendsList;
        }

        @Override

        public FriendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.view_textview, parent, false);
            return new FriendsViewHolder(view);

        }

        @Override
        public void onBindViewHolder(@NonNull FriendsAdapter.FriendsViewHolder holder, int position) {
            Friends friends = friendsList.get(position);
            holder.textView.setText(friends.getName_name() + "\n" + friends.getMail_mail() + "\n" + friends.getPhone_phone());

        }

        @Override
        public int getItemCount() {
            return friendsList.size();
        }

        // class ClientViewHolder extends RecyclerView.ViewHolder{
        class FriendsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView textViewStatus, textView, textViewDesc, textViewFinishBy;
            ImageView menubtn;


            public FriendsViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.textView);
                menubtn = (ImageView) itemView.findViewById(R.id.menubt);

                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                PopupMenu p = new PopupMenu(context, menubtn);
                p.getMenuInflater().inflate(R.menu.menu2, p.getMenu());
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                Friends friends = friendsList.get(getAdapterPosition());
                                Intent intent1 = new Intent(context, Edit.class);
                                intent1.putExtra("friends", friends);
                                context.startActivity(intent1);
                                return true;
                            case R.id.delete:
                                friends = friendsList.get(getAdapterPosition());
                                alert(friends);
                                return true;
                            default:
                                return true;
                        }
                    }
                });
                p.show();
            }
        }
        private void alert(final Friends friends) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Are you sure you want to delete?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    deleteTask(friends);
                    Intent intent = new Intent(context, AddFriends.class);
                    context.startActivity(intent);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });

            AlertDialog ad = builder.create();
            ad.show();
        }


        public void deleteTask(final Friends friends) {
            class DeleteTask extends AsyncTask<Void, Void, Void> {

                @Override
                protected Void doInBackground(Void... voids) {
                    DatabaseClient.getInstance(context.getApplicationContext())
                            .getAppDataBase()
                            .Dao()
                            .delete(friends);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Toast.makeText(context.getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                    finish();
                    Intent intent2 = new Intent(context, Edit.class);
                    context.startActivity(intent2);
                }

                private void finish() {
                }


            }

            DeleteTask dt = new DeleteTask();
            dt.execute();

        }

}



