package com.example.hispeed.calculatoroil;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Developer extends Fragment implements NavigationView.OnNavigationItemSelectedListener{
    Intent emailIntent;

    EditText edt_title_gmail;
    EditText edt_subject_gmail;
    EditText edt_text_gmail;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("ผู้พัฒนา");
        return inflater.inflate(R.layout.activity_developer, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);

        edt_title_gmail = (EditText) getActivity().findViewById(R.id.edt_title_gmail);
        edt_title_gmail.setText("Siliverlose@gmail.com");
        edt_subject_gmail = (EditText) getActivity().findViewById(R.id.edt_subject_gmail);
        edt_subject_gmail.requestFocus();
        edt_text_gmail = (EditText) getActivity().findViewById(R.id.edt_text_gmail);


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.send, menu);

        return;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()){
            case R.id.btn_send:

                if (!(edt_title_gmail.getText().toString().equals("Siliverlose@gmail.com"))) {
                    Toast.makeText(getActivity(), "จีเมล์ของผู้รับไม่ถูกต้อง", Toast.LENGTH_SHORT).show();

                } else if (edt_subject_gmail.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "กรุณาเขียนชื่อเรื่อง", Toast.LENGTH_SHORT).show();

                } else if (edt_text_gmail.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "กรุณาเขียนข้อความที่ต้องการส่ง", Toast.LENGTH_SHORT).show();

                } else {
                    emailIntent = new Intent(Intent.ACTION_SEND);
                    String to = edt_title_gmail.getText().toString();
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, edt_subject_gmail.getText().toString());
                    emailIntent.putExtra(Intent.EXTRA_TEXT, edt_text_gmail.getText().toString());
                    emailIntent.setType("message/rfc822");
                    startActivity(Intent.createChooser(emailIntent, "Send Email"));
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}

