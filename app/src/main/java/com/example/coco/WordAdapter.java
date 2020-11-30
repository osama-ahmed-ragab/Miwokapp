package com.example.coco;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    public WordAdapter(NumbersActivity numbersActivity, ArrayList<Word> words, int colorResourceId) {
        super(numbersActivity, 0, words);
        mColorResourceId = colorResourceId;

    }

    public WordAdapter(FamilyActivity familyActivity, ArrayList<Word> words, int colorResourceId) {
        super(familyActivity, 0, words);
        mColorResourceId = colorResourceId;
    }

    public WordAdapter(ColorsActivity colorsActivity, ArrayList<Word> words, int colorResourceId) {
        super(colorsActivity, 0, words);
        mColorResourceId = colorResourceId;
    }

    public WordAdapter(PhrasesActivity phrasesActivity, ArrayList<Word> words, int colorResourceId) {
        super(phrasesActivity, 0, words);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position,
                        View convertView,
                        ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView englishword = (TextView) listItemView.findViewById(R.id.first);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        englishword.setText(currentWord.getDefaultTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView miwokword = (TextView) listItemView.findViewById(R.id.second);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        miwokword.setText(currentWord.getMiwokTranslation());

        // Find the ImageView in the list_item.xml layout with the ID image
        ImageView imagesrc = (ImageView) listItemView.findViewById(R.id.image);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to imagesrc

        if (currentWord.hasImage()) {
            imagesrc.setImageResource(currentWord.getImageResourceId());
            imagesrc.setVisibility(View.VISIBLE);
            // Return the whole list item layout (containing 2 TextViews )
            // so that it can be shown in the ListView
        } else {
            imagesrc.setVisibility(View.GONE);
        }
        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);
        return listItemView;
    }
}
