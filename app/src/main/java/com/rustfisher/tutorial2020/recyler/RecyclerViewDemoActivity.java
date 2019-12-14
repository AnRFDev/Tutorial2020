package com.rustfisher.tutorial2020.recyler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    }


    private class LetterAdapter extends RecyclerView.Adapter<VH> {

        private List<Character> dataList;

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
            Character c = dataList.get(position);
            holder.tv1.setText(c.toString());
            holder.tv2.setText(String.valueOf(Integer.valueOf(c)));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }

    private class VH extends RecyclerView.ViewHolder {
        TextView tv1;
        TextView tv2;

        public VH(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
        }
    }
}
