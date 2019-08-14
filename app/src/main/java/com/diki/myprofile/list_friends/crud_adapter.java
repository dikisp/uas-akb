package com.diki.myprofile.list_friends;
//  3 Agustus
// 10116352
// DIKI SUPRIADI
// IF-8
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.diki.myprofile.Model.AppDatabase;
import com.diki.myprofile.Model.Friend;
import com.diki.myprofile.Model.FriendData;
import com.diki.myprofile.R;

import java.util.ArrayList;

/**
 * Created by Oclemy for ProgrammingWizards TV Channel and https://www.camposha.info.
 */
public class crud_adapter extends RecyclerView.Adapter<crud_adapter.ViewHolder>{

    private ArrayList<FriendData> daftarFriend;
    private Context context;
    private AppDatabase db;

    public crud_adapter(ArrayList<FriendData> friends, Context ctx) {
        /**
         * Inisiasi data dan variabel yang akan digunakan
         */
        daftarFriend = friends;
        context = ctx;

        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "db").allowMainThreadQueries().build();

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * Inisiasi View
         * Di tutorial ini kita hanya menggunakan data String untuk tiap item
         * dan juga view nya hanyalah satu TextView
         */
        TextView tvTitle;
        CardView cvMain;

        ViewHolder(View v) {
            super(v);
            tvTitle = v.findViewById(R.id.tv_friend_nama);
            cvMain = v.findViewById(R.id.cv_friend);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final String name = daftarFriend.get(position).getNama();

        holder.tvTitle.setText(name);

        holder.cvMain.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.view_dialog);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

                Button editButton = dialog.findViewById(R.id.bt_edit_data);
                Button delButton = dialog.findViewById(R.id.bt_delete_data);

                //apabila tombol edit diklik
                editButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                onEditBarang(position);
                            }
                        }
                );

                //apabila tombol delete diklik
                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                onDeteleBarang(position);
                            }
                        }
                );
                return true;
            }
        });
        holder.tvTitle.setText(name);
    }

    private void onDeteleBarang(int position) {
        db.dao().deleteFriend(daftarFriend.get(position));
        daftarFriend.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, daftarFriend.size());
    }

    private void onEditBarang(int position) {
        context.startActivity(AddFriend.getActIntent((Activity) context).putExtra("data", (Parcelable) daftarFriend.get(position)));
    }


    @Override
    public int getItemCount() {

        return daftarFriend.size();
    }
}
