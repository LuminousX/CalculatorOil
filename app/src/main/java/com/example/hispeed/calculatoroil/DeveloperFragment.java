package com.example.hispeed.calculatoroil;

import android.content.Intent;
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
import android.widget.Toast;

public class DeveloperFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    private Intent emailIntent;

    private EditText edtTitleGmail, edtSubjectGmail, edtTextGmail;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("ผู้พัฒนา");
        return inflater.inflate(R.layout.fragment_developer, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);

        bindView();

        edtTitleGmail.setText("Siliverlose@gmail.com");
        edtSubjectGmail.requestFocus();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.send, menu);
        return;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_send:

                if (!(edtTitleGmail.getText().toString().equals("Siliverlose@gmail.com"))) {
                    Toast.makeText(getActivity(), "จีเมล์ของผู้รับไม่ถูกต้อง", Toast.LENGTH_SHORT).show();

                } else if (edtSubjectGmail.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "กรุณาเขียนชื่อเรื่อง", Toast.LENGTH_SHORT).show();

                } else if (edtTextGmail.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "กรุณาเขียนข้อความที่ต้องการส่ง", Toast.LENGTH_SHORT).show();

                } else {
                    emailIntent = new Intent(Intent.ACTION_SEND);
                    String to = edtTitleGmail.getText().toString();
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, edtSubjectGmail.getText().toString());
                    emailIntent.putExtra(Intent.EXTRA_TEXT, edtTextGmail.getText().toString());
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

    private void bindView() {
        edtTitleGmail = (EditText) getActivity().findViewById(R.id.edt_title_gmail);
        edtSubjectGmail = (EditText) getActivity().findViewById(R.id.edt_subject_gmail);
        edtTextGmail = (EditText) getActivity().findViewById(R.id.edt_text_gmail);
    }
}

