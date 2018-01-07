package com.example.hispeed.calculatoroil;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class EcoSticker extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Eco Sticker");
        return inflater.inflate(R.layout.activity_eco_sticker, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_eco_sticker = (Button) getActivity().findViewById(R.id.btn_eco_sticker);
        btn_eco_sticker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = intentEcoSticker(getActivity());
                startActivity(intent);
            }
        });


        ImageView imgCargif = (ImageView)getActivity().findViewById(R.id.ImgCargif);
        imgCargif.setBackgroundResource(R.drawable.cargif);
        AnimationDrawable animationDrawable = (AnimationDrawable)imgCargif.getBackground();
        animationDrawable.start();

    }


    public Intent intentEcoSticker(Context context) {
        try {
            context.getPackageManager().getPackageInfo("http://car.go.th", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("http://car.go.th"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("http://car.go.th"));
        }
    }

}
