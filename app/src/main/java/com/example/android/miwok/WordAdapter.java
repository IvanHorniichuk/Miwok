package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {


    private int  mBackgroundColorId;
    public WordAdapter(@NonNull Context context,@NonNull List<Word> objects,int backgroundColorId) {
        super(context, 0, objects);
        mBackgroundColorId=backgroundColorId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem=convertView;
        if(listItem==null)
        {
            listItem= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        TextView tv=(TextView)listItem.findViewById(R.id.miwokWord);
        final Word word= getItem(position);
        tv.setText(word.getMiwokTranslation());
        tv=(TextView)listItem.findViewById(R.id.englishWord);
        tv.setText(word.getDefaultTranslation());
        ImageView iv=(ImageView)listItem.findViewById(R.id.itemImage);
        int imageId=word.getImageID();
        if(imageId==0)
        {
            iv.setVisibility(View.GONE);
        }
        else
        {
            iv.setImageResource(word.getImageID());
        }
        listItem.setBackgroundColor(getContext().getResources().getColor(mBackgroundColorId));
        return listItem;
    }


}

