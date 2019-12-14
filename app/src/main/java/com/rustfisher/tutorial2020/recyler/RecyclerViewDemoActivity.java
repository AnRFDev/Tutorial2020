package com.rustfisher.tutorial2020.recyler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rustfisher.tutorial2020.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019-12-14
 */
public class RecyclerViewDemoActivity extends AppCompatActivity {

    LetterAdapter mLetterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler_view_demo);

        List<Character> characterList = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            characterList.add(c);
        }

        mLetterAdapter = new LetterAdapter(characterList);
        RecyclerView letterReView = findViewById(R.id.re_view);
        letterReView.setAdapter(mLetterAdapter);
        letterReView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        mLetterAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Character c) {
                Toast.makeText(getApplicationContext(), "Click " + c, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(Character c) {
                Toast.makeText(getApplicationContext(), "Long click " + c, Toast.LENGTH_LONG).show();
            }
        });

        mLetterAdapter.setAbsOnItemClick(new AbsOnItemClick() {
            @Override
            public void onClick(char c) {
                Log.d("rustApp", "[abs] onClick: " + c);
            }
        });

    }


    private class LetterAdapter extends RecyclerView.Adapter<VH> {

        private List<Character> dataList;
        private OnItemClickListener onItemClickListener;
        private AbsOnItemClick absOnItemClick;

        public LetterAdapter(List<Character> dataList) {
            this.dataList = dataList;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_letter, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            final Character c = dataList.get(position);
            holder.tv1.setText(c.toString());
            holder.tv2.setText(String.valueOf(Integer.valueOf(c)));
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(c);
                    }
                    if (absOnItemClick != null) {
                        absOnItemClick.onClick(c);
                    }
                }
            });
            holder.item.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemLongClick(c);
                    }
                    if (absOnItemClick != null) {
                        absOnItemClick.onLongClick(c);
                    }
                    return true;
                }
            });
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }


        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        public void setAbsOnItemClick(AbsOnItemClick absOnItemClick) {
            this.absOnItemClick = absOnItemClick;
        }
    }

    private class VH extends RecyclerView.ViewHolder {
        View item;
        TextView tv1;
        TextView tv2;

        public VH(@NonNull View itemView) {
            super(itemView);
            item = itemView;
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
        }
    }
}
