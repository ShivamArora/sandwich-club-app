package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView mAlsoKnownAsTv, mPlaceOfOriginTv, mDescriptionTv, mIngredientsTv;

    private Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        mAlsoKnownAsTv = findViewById(R.id.tv_also_known_as);
        mPlaceOfOriginTv = findViewById(R.id.tv_place_of_origin);
        mIngredientsTv = findViewById(R.id.tv_ingredients);
        mDescriptionTv = findViewById(R.id.tv_description);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

        String alsoKnownAs = sandwich.getAlsoKnownAs().toString();
        mAlsoKnownAsTv.setText(alsoKnownAs.substring(1, alsoKnownAs.length() - 1));

        mPlaceOfOriginTv.setText(sandwich.getPlaceOfOrigin());

        String ingredients = sandwich.getIngredients().toString();
        mIngredientsTv.setText(ingredients.substring(1, ingredients.length() - 1));
        mDescriptionTv.setText(sandwich.getDescription());
    }
}
