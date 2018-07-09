package com.example.hispeed.calculatoroil;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hispeed.calculatoroil.Models.DatabaseOil;
import com.example.hispeed.calculatoroil.Models.DetailCar;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class CalculateOilFragment extends Fragment {

    public CalculateOilFragment() {
    }

    private Button btnCalculate;

    String[] arrayOil = {"ประเภทน้ำมัน", "แก๊สโซฮอล 95", "แก๊สโซฮอล 91", "แก๊สโซฮอล E20", "แก๊สโซฮอล E85", "ดีเซล", "เบนซิน 95"};
    String[] typeNull = {"รุ่น"};

    private Spinner spinTypeCar, spinTypeBrand, spinTypeOil;

    private String distanceText, durationText, startLocation, endLocation, strDate;

    private TextView textCalDistance, textCalDuration, startTextLocation, endTextLocation, textTypeOil, textNameCar, calOil, calMoney, textTypeCar, averageBaht, textSave;

    private double calAmountOil, calSpendOil, distance, duration;

    private DetailCar detailCar = new DetailCar();

    private RadioButton radioTown, radioCountrySide, radioCombined;
    private int point;

    private EditText edtOilPrice;
    private ImageView imageSearchOilPrice;

    private RelativeLayout relativeLayout, relativeTextSave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        distance = Double.parseDouble(String.format("%.1f", Double.parseDouble(getArguments().getString("distanceIntent")) / 1000));
        duration = Double.parseDouble(getArguments().getString("durationIntent"));
        distanceText = getArguments().getString("distanceText");
        durationText = getArguments().getString("durationText");
        startLocation = getArguments().getString("startLocation");
        endLocation = getArguments().getString("endLocation");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.transition_move));
        }
        getActivity().setTitle("แผนที่");
        return inflater.inflate(R.layout.fragment_calculate_oil, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);

        bindView();
        carTypeNull();

        spinTypeCar.setAdapter(showAdapter(detailCar.type_car));
        spinTypeOil.setAdapter(showAdapter(arrayOil));

        spinTypeCar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectTypeCar();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCalClick();
            }
        });

        textSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();
            }
        });

        imageSearchOilPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(intentOilPrice(getActivity())));
            }
        });
    }

    public void btnCalClick() {
        if (spinTypeCar.getSelectedItemPosition() == 0) {
            Toast.makeText(getActivity(), "กรุณาใส่ยี่ห้อรถยนต์", Toast.LENGTH_SHORT).show();
            return;
        } else if (spinTypeBrand.getSelectedItemPosition() == 0) {
            Toast.makeText(getActivity(), "กรุณาใส่รุ่นรถยนต์", Toast.LENGTH_SHORT).show();
            return;
        } else if (spinTypeOil.getSelectedItemPosition() == 0) {
            Toast.makeText(getActivity(), "กรุณาใส่ประเภทน้ำมัน", Toast.LENGTH_SHORT).show();
            return;
        } else if (!radioCombined.isChecked() && !radioCountrySide.isChecked() && !radioTown.isChecked()) {
            Toast.makeText(getActivity(), "กรุณาใส่สภาวะการใช้งานรถยนต์", Toast.LENGTH_SHORT).show();
            return;
        } else if (edtOilPrice.getText().toString().length() == 0) {
            Toast.makeText(getActivity(), "กรุณาใส่ค่าน้ำมัน", Toast.LENGTH_SHORT).show();
            return;
        } else if (spinTypeBrand.getSelectedItemPosition() == 14) {
            Toast.makeText(getActivity(), "ไม่พบข้อมูลของรถยนต์รุ่นนี้", Toast.LENGTH_SHORT).show();
            return;
        } else {
            relativeLayout.setAnimation(showAnimation(relativeLayout));
            relativeTextSave.setAnimation(showAnimation(relativeTextSave));

            priceOil(Double.parseDouble(edtOilPrice.getText().toString()));

            textCalDistance.setText(distanceText);
            textCalDuration.setText(durationText);
            startTextLocation.setText(startLocation);
            endTextLocation.setText(endLocation);
            textNameCar.setText(spinTypeCar.getSelectedItem().toString());
            textTypeCar.setText(spinTypeBrand.getSelectedItem().toString());
            textTypeOil.setText(spinTypeOil.getSelectedItem().toString() + " (" + edtOilPrice.getText().toString() + " บาท)");
            calOil.setText(String.format("%.2f", calAmountOil) + " L.");
            calMoney.setText(decimalFormatText(calSpendOil) + " บาท");

            averageBaht.setText(String.format("%.2f", calSpendOil / distance) + " บาท ต่อ 1 km.");

            edtOilPrice.onEditorAction(EditorInfo.IME_ACTION_DONE);
        }
    }

    public String decimalFormatText(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.0");
        return decimalFormat.format(value);
    }

    public Animation showAnimation(RelativeLayout layout) {
        layout.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.transition_save);
        animation.reset();
        layout.clearAnimation();
        return animation;
    }

    public ArrayAdapter<String> showAdapter(String[] strings) {
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, strings);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapterType;
    }

    public void bindView() {
        spinTypeCar = (Spinner) getActivity().findViewById(R.id.spinTpye);
        spinTypeOil = (Spinner) getActivity().findViewById(R.id.spinOil);
        spinTypeBrand = (Spinner) getActivity().findViewById(R.id.spinType2);

        relativeLayout = (RelativeLayout) getActivity().findViewById(R.id.relativeOil2);
        relativeTextSave = (RelativeLayout) getActivity().findViewById(R.id.relative_text_save);

        radioTown = (RadioButton) getActivity().findViewById(R.id.radioTown);
        radioCountrySide = (RadioButton) getActivity().findViewById(R.id.radopCountrySide);
        radioCombined = (RadioButton) getActivity().findViewById(R.id.radioCombined);

        btnCalculate = (Button) getActivity().findViewById(R.id.btncal);

        textTypeOil = (TextView) getActivity().findViewById(R.id.textTypeOil);
        textCalDistance = (TextView) getActivity().findViewById(R.id.distanceTextOil);
        textCalDuration = (TextView) getActivity().findViewById(R.id.durationTextOil);
        startTextLocation = (TextView) getActivity().findViewById(R.id.startTextLocation);
        endTextLocation = (TextView) getActivity().findViewById(R.id.endTextLocation);
        textNameCar = (TextView) getActivity().findViewById(R.id.textNameCar);
        calOil = (TextView) getActivity().findViewById(R.id.calOil);
        calMoney = (TextView) getActivity().findViewById(R.id.calMoney);
        textTypeCar = (TextView) getActivity().findViewById(R.id.textTypeCar);
        averageBaht = (TextView) getActivity().findViewById(R.id.average);
        textSave = (TextView) getActivity().findViewById(R.id.textSave);

        edtOilPrice = (EditText) getActivity().findViewById(R.id.edt_oil_price);

        imageSearchOilPrice = (ImageView) getActivity().findViewById(R.id.image_search_oil_price);
    }

    public String getDateCurrent() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC+7"));
        return strDate = simpleDateFormat.format(new Date());
    }

    public Intent intentOilPrice(Context context) {
        try {
            context.getPackageManager().getPackageInfo("http://gasprice.kapook.com/gasprice.php", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("http://gasprice.kapook.com/gasprice.php"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("http://gasprice.kapook.com/gasprice.php"));
        }
    }

    public void carTypeNull() {
        final ArrayAdapter<String> adapterTypeNull = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, typeNull);
        adapterTypeNull.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (spinTypeCar.getSelectedItemPosition() == 0) {
            spinTypeBrand.setAdapter(adapterTypeNull);
        }
    }

    public void checkData() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogStyle);
        if (textNameCar.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "กรุณาใส่ข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
            return;
        } else {
            builder.setTitle("บันทึกข้อมูล")
                    .setMessage("คุณต้องการบันทึกข้อมูลนี้หรือไม่?")
                    .setCancelable(false)
                    .setNegativeButton("ไม่ใช่", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    saveData();

                    HistoryFragment historyFragment = new HistoryFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.enter_right, R.anim.exit_left, R.anim.enter_left, R.anim.exit_right);
                    fragmentTransaction.replace(R.id.fragment_continer, historyFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    public void selectTypeCar() {
        switch ((int) spinTypeCar.getSelectedItemId()) {
            case 0:
                carTypeNull();
                break;
            case 1:
                showAdapterTypeBrand(detailCar.bmw);
                break;
            case 2:
                showAdapterTypeBrand(detailCar.chevrolet);
                break;
            case 3:
                showAdapterTypeBrand(detailCar.ford);
                break;
            case 4:
                showAdapterTypeBrand(detailCar.foton);
                break;
            case 5:
                showAdapterTypeBrand(detailCar.honda);
                break;
            case 6:
                showAdapterTypeBrand(detailCar.isuzu);
                break;
            case 7:
                showAdapterTypeBrand(detailCar.mazda);
                break;
            case 8:
                showAdapterTypeBrand(detailCar.mercedes_benz);
                break;
            case 9:
                showAdapterTypeBrand(detailCar.mg);
                break;
            case 10:
                showAdapterTypeBrand(detailCar.mini);
                break;
            case 11:
                showAdapterTypeBrand(detailCar.mitsubishi);
                break;
            case 12:
                showAdapterTypeBrand(detailCar.nissan);
                break;
            case 13:
                showAdapterTypeBrand(detailCar.suzuki);
                break;
            case 14:
                showAdapterTypeBrand(detailCar.tata);
                break;
            case 15:
                showAdapterTypeBrand(detailCar.toyota);
                break;
        }
    }

    public void showAdapterTypeBrand(String[] car) {
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, car);
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTypeBrand.setAdapter(stringArrayAdapter);
    }

    public void checkTypeCar() {
        point = spinTypeBrand.getSelectedItemPosition();
        switch ((int) spinTypeCar.getSelectedItemId()) {
            case 1:
                checkRadioButton(detailCar.bmw_town[point], detailCar.bmw_countryside[point], detailCar.bmw_combined[point]);
                break;
            case 2:
                checkRadioButton(detailCar.chevrolet_town[point], detailCar.chevrolet_countryside[point], detailCar.chevrolet_combined[point]);
                break;
            case 3:
                checkRadioButton(detailCar.ford_town[point], detailCar.ford_countryside[point], detailCar.ford_combined[point]);
                break;
            case 4:
                checkRadioButton(detailCar.foton_town[point], detailCar.foton_countryside[point], detailCar.foton_combined[point]);
                break;
            case 5:
                checkRadioButton(detailCar.honda_town[point], detailCar.honda_countryside[point], detailCar.honda_combined[point]);
                break;
            case 6:
                checkRadioButton(detailCar.isuzu_town[point], detailCar.isuzu_countryside[point], detailCar.isuzu_combined[point]);
                break;
            case 7:
                checkRadioButton(detailCar.mazda_town[point], detailCar.mazda_countryside[point], detailCar.mazda_combined[point]);
                break;
            case 8:
                checkRadioButton(detailCar.mercedes_benz_town[point], detailCar.mercedes_benz_countryside[point], detailCar.mercedes_benz_combined[point]);
                break;
            case 9:
                checkRadioButton(detailCar.mg_town[point], detailCar.mg_countryside[point], detailCar.mg_combined[point]);
                break;
            case 10:
                checkRadioButton(detailCar.mini_town[point], detailCar.mini_countryside[point], detailCar.mini_combined[point]);
                break;
            case 11:
                checkRadioButton(detailCar.mitsubishi_town[point], detailCar.mitsubishi_countryside[point], detailCar.mitsubishi_combined[point]);
                break;
            case 12:
                checkRadioButton(detailCar.nissan_town[point], detailCar.nissan_countryside[point], detailCar.nissan_combined[point]);
                break;
            case 13:
                checkRadioButton(detailCar.suzuki_town[point], detailCar.suzuki_countryside[point], detailCar.suzuki_combined[point]);
                break;
            case 14:
                checkRadioButton(detailCar.tata_town[point], detailCar.tata_countryside[point], detailCar.tata_combined[point]);
                break;
            case 15:
                checkRadioButton(detailCar.toyota_town[point], detailCar.toyota_countryside[point], detailCar.toyota_combined[point]);
                break;
        }
    }

    public void checkRadioButton(double town, double countryside, double combine) {
        if (radioTown.isChecked()) {
            calAmountOil = distance / town;
        } else if (radioCountrySide.isChecked()) {
            calAmountOil = distance / countryside;
        } else if (radioCombined.isChecked()) {
            calAmountOil = distance / combine;
        }
    }

    public void priceOil(Double price) {
        checkTypeCar();
        calSpendOil = Double.parseDouble(String.format("%.2f", calAmountOil * price));
    }

    private void saveData() {
        final DatabaseOil databaseOil = new DatabaseOil(getActivity());
        try {
            databaseOil.insertData(textNameCar.getText().toString(), textTypeCar.getText().toString(), startTextLocation.getText().toString(), endTextLocation.getText().toString(), textCalDistance.getText().toString()
                    , textCalDuration.getText().toString(), textTypeOil.getText().toString(), calOil.getText().toString(), calMoney.getText().toString(), getDateCurrent(), averageBaht.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
