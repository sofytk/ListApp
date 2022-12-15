package com.example.listapp;

import static com.example.listapp.R.color.black;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listapp.databinding.ListItemBinding;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity{

    public boolean isClicked = false;
    public int likeCounter = 0;
    public View convertView;

    ListItemBinding binding;
    private ListView listView;
    private ArrayAdapter<News> adapter;

    private final News[] news = new News[7];

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        news[0] = new News("Коты против собак: кто популярнее?");
        news[1] = new News("Иван задает объемное дз. Разбираемся что к чему");
        news[2] = new News("Вставать в 7 утра: проблема или полезная привычка");
        news[3] = new News("Музыкальный фестиваль прошел в городе N");
        news[4] = new News("Очереди за билетами на \"Щелкунчика\"");
        news[5] = new News("Какой IPhone выбрать в 2022 году");
        news[6] = new News("Ученые нашли новый опасный вирус");

        listView = findViewById(R.id.list_view);
        adapter = new MyNewsAdapter(this, news);
        listView.setAdapter(adapter);

        binding = ListItemBinding.inflate(getLayoutInflater());


    }

    public class MyNewsAdapter extends ArrayAdapter<News> {

        final News[] news;

        public MyNewsAdapter(@NonNull Context context, News[] news) {
            super(context, R.layout.list_item);
            this.news = news;
        }

        @Override
        public int getCount() {
            return news.length;
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            News news1 = news[position];

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
            }

            TextView monthName = convertView.findViewById(R.id.title);
            monthName.setText(news1.name);

            ImageView imageView = convertView.findViewById(R.id.preview);
            switch (position) {
                case 0:
                    imageView.setImageResource(R.drawable.img);
                    break;
                case 1:
                    imageView.setImageResource(R.drawable.dz);
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.seven_am);
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.festival);
                    break;
                case 4:
                    imageView.setImageResource(R.drawable.shelk);
                    break;
                case 5:
                    imageView.setImageResource(R.drawable.iphones);
                    break;
                case 6:
                    imageView.setImageResource(R.drawable.virus);
                    break;
            }

            Button like = convertView.findViewById(R.id.like);
            TextView LikeCounter = convertView.findViewById(R.id.like_counter);

            View finalConvertView = convertView;
            convertView.findViewById(R.id.like).setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    if (isClicked) {
                        like.setBackgroundResource(R.drawable.like);
                        likeCounter--;
                        LikeCounter.setText(String.valueOf(likeCounter));
                        isClicked = false;
                        Log.d("RRRRR", "clickkkkk");
                    } else {
                        like.setBackgroundResource(R.drawable.like_click);
                        likeCounter++;
                        LikeCounter.setText(String.valueOf(likeCounter));
                        Log.d("RRRRR", "click");
                        isClicked = true;
                    }

                }
            });


            Button share = convertView.findViewById(R.id.share);
            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Поделиться", Toast.LENGTH_SHORT).show();
                }
            });

            Button comments = convertView.findViewById(R.id.comments);
            comments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.exit:
                            finish();
                            break;
                        case R.id.comments:
                            Intent intent = new Intent(MainActivity.this, CommentAct.class);
                            Comments comments = new Comments(news1.name);
                            intent.putExtra("com", comments);
                            startActivity(intent);
                            break;
                    }
                }
            });

            return convertView;
        }
    }
}