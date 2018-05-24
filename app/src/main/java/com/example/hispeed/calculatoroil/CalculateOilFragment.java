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


public class CalculateOilFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    public CalculateOilFragment() {
    }

    private Button btncal;

    String[] arrayOil = {"ประเภทน้ำมัน", "แก๊สโซฮอล 95", "แก๊สโซฮอล 91", "แก๊สโซฮอล E20", "แก๊สโซฮอล E85", "ดีเซล", "เบนซิน 95"};
    String[] type_null = {"รุ่น"};

    private Spinner spinType, spinOil, spinType2;

    private String distanceStringIntent, durationStringIntent, distanceTextIntent, durationTextIntent, startLocation, endLocation, str_date, str_average_baht, distanceText;

    private TextView textCalDis, textCalDu, startTextLocation, endTextLocation, textTypeOil, textNameCar, calOil, calMoney, textTypeCar, average_baht, textsave;

    private double calAmountOil, calSpentOil, distanceInDouble;

    public DetailCar detailCar = new DetailCar();

    public RadioButton radioTown, radioCountrySide, radioCombined;
    public int point;

    public EditText edt_oil_price;
    public ImageView image_search_oil_price;

    double oil_price_search;

    RelativeLayout relativeLayout, relative_text_save;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        distanceStringIntent = getArguments().getString("distanceIntent");
        durationStringIntent = getArguments().getString("durationIntent");
        distanceTextIntent = getArguments().getString("distanceText");
        durationTextIntent = getArguments().getString("durationText");
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

        spinType.setAdapter(showAdapter(detailCar.type_car));
        spinOil.setAdapter(showAdapter(arrayOil));

        spinType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectTypeCar();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btncal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCalClick();
            }
        });

        textsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();
            }
        });

        image_search_oil_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = intentOilPrice(getActivity());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void btnCalClick() {
        if (spinType.getSelectedItemPosition() == 0) {
            Toast.makeText(getActivity(), "กรุณาใส่ยี่ห้อรถยนต์", Toast.LENGTH_SHORT).show();
            return;
        } else if (spinType2.getSelectedItemPosition() == 0) {
            Toast.makeText(getActivity(), "กรุณาใส่รุ่นรถยนต์", Toast.LENGTH_SHORT).show();
            return;
        } else if (spinOil.getSelectedItemPosition() == 0) {
            Toast.makeText(getActivity(), "กรุณาใส่ประเภทน้ำมัน", Toast.LENGTH_SHORT).show();
            return;
        } else if (!radioCombined.isChecked() && !radioCountrySide.isChecked() && !radioTown.isChecked()) {
            Toast.makeText(getActivity(), "กรุณาใส่สภาวะการใช้งานรถยนต์", Toast.LENGTH_SHORT).show();
            return;
        } else if (edt_oil_price.getText().toString().length() == 0) {
            Toast.makeText(getActivity(), "กรุณาใส่ค่าน้ำมัน", Toast.LENGTH_SHORT).show();
            return;
        } else if (spinType2.getSelectedItemPosition() == 14) {
            Toast.makeText(getActivity(), "ไม่พบข้อมูลของรถยนต์รุ่นนี้", Toast.LENGTH_SHORT).show();
            return;
        } else {

            relativeLayout.setAnimation(showAnimation(relativeLayout));
            relative_text_save.setAnimation(showAnimation(relative_text_save));

            oil_price_search = Double.parseDouble(edt_oil_price.getText().toString());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC+7"));
            str_date = simpleDateFormat.format(new Date());

            distanceText = String.format("%.1f", Double.parseDouble(distanceStringIntent) / 1000);
            double distanceDouble = Double.parseDouble(distanceText);

            checkPriceOil();

            String calMoneyText = String.format("%.2f", calSpentOil);
            double calMoneyDouble = Double.parseDouble(calMoneyText);

            textCalDis.setText(decimalFormatText(distanceDouble) + " km.");
            textCalDu.setText(durationTextIntent);
            startTextLocation.setText(startLocation);
            endTextLocation.setText(endLocation);
            textNameCar.setText(spinType.getSelectedItem().toString());
            textTypeCar.setText(spinType2.getSelectedItem().toString());
            textTypeOil.setText(spinOil.getSelectedItem().toString() + " (" + oil_price_search + " บาท)");
            calOil.setText(String.format("%.2f", calAmountOil) + " L.");
            calMoney.setText(decimalFormatText(calMoneyDouble) + " บาท");

            average_baht.setText(String.format("%.2f", calSpentOil / distanceInDouble) + " บาท ต่อ 1 km.");
            str_average_baht = average_baht.getText().toString();

            edt_oil_price.onEditorAction(EditorInfo.IME_ACTION_DONE);
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

    private void bindView() {
        spinType = (Spinner) getActivity().findViewById(R.id.spinTpye);
        spinOil = (Spinner) getActivity().findViewById(R.id.spinOil);
        spinType2 = (Spinner) getActivity().findViewById(R.id.spinType2);

        relativeLayout = (RelativeLayout) getActivity().findViewById(R.id.relativeOil2);
        relative_text_save = (RelativeLayout) getActivity().findViewById(R.id.relative_text_save);

        radioTown = (RadioButton) getActivity().findViewById(R.id.radioTown);
        radioCountrySide = (RadioButton) getActivity().findViewById(R.id.radopCountrySide);
        radioCombined = (RadioButton) getActivity().findViewById(R.id.radioCombined);

        btncal = (Button) getActivity().findViewById(R.id.btncal);

        textTypeOil = (TextView) getActivity().findViewById(R.id.textTypeOil);
        textCalDis = (TextView) getActivity().findViewById(R.id.distanceTextOil);
        textCalDu = (TextView) getActivity().findViewById(R.id.durationTextOil);
        startTextLocation = (TextView) getActivity().findViewById(R.id.startTextLocation);
        endTextLocation = (TextView) getActivity().findViewById(R.id.endTextLocation);
        textNameCar = (TextView) getActivity().findViewById(R.id.textNameCar);
        calOil = (TextView) getActivity().findViewById(R.id.calOil);
        calMoney = (TextView) getActivity().findViewById(R.id.calMoney);
        textTypeCar = (TextView) getActivity().findViewById(R.id.textTypeCar);

        average_baht = (TextView) getActivity().findViewById(R.id.average);

        edt_oil_price = (EditText) getActivity().findViewById(R.id.edt_oil_price);
        image_search_oil_price = (ImageView) getActivity().findViewById(R.id.image_search_oil_price);

        textsave = (TextView) getActivity().findViewById(R.id.textSave);
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
                android.R.layout.simple_spinner_item, type_null);
        adapterTypeNull.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (spinType.getSelectedItemPosition() == 0) {
            spinType2.setAdapter(adapterTypeNull);
        }
    }

    public void checkData() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogStyle);
        if (textNameCar.getText().equals("")) {
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
        switch ((int) spinType.getSelectedItemId()) {
            case 0:
                carTypeNull();
                break;

            case 1:
                showAdapterTypeCar(detailCar.bmw);
                break;

            case 2:
                showAdapterTypeCar(detailCar.chevrolet);
                break;

            case 3:
                showAdapterTypeCar(detailCar.ford);
                break;

            case 4:
                showAdapterTypeCar(detailCar.foton);
                break;

            case 5:
                showAdapterTypeCar(detailCar.honda);
                break;

            case 6:
                showAdapterTypeCar(detailCar.isuzu);
                break;

            case 7:
                showAdapterTypeCar(detailCar.mazda);
                break;

            case 8:
                showAdapterTypeCar(detailCar.mercedes_benz);
                break;

            case 9:
                showAdapterTypeCar(detailCar.mg);
                break;

            case 10:
                showAdapterTypeCar(detailCar.mini);
                break;

            case 11:
                showAdapterTypeCar(detailCar.mitsubishi);
                break;

            case 12:
                showAdapterTypeCar(detailCar.nissan);
                break;

            case 13:
                showAdapterTypeCar(detailCar.suzuki);
                break;

            case 14:
                showAdapterTypeCar(detailCar.tata);
                break;

            case 15:
                showAdapterTypeCar(detailCar.toyota);
                break;
        }
    }

    public void showAdapterTypeCar(String[] car) {
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, car);
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinType2.setAdapter(stringArrayAdapter);
    }

    public void checkTypeCar() {
        switch ((int) spinType.getSelectedItemId()) {
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
            calAmountOil = distanceInDouble / town;
        } else if (radioCountrySide.isChecked()) {
            calAmountOil = distanceInDouble / countryside;
        } else if (radioCombined.isChecked()) {
            calAmountOil = distanceInDouble / combine;
        }
    }

    public void checkPriceOil() {
        distanceInDouble = Double.parseDouble(distanceText);
        point = spinType2.getSelectedItemPosition();
        priceOil(oil_price_search);
    }

    public Double priceOil(Double price) {
        checkTypeCar();
        String strCalAmountOil = String.format("%.2f", calAmountOil);
        calSpentOil = Double.parseDouble(strCalAmountOil) * price;
        return calSpentOil;
    }

    private void saveData() {
        final DatabaseOil databaseOil = new DatabaseOil(getActivity());

        long save = databaseOil.insertData(textNameCar.getText().toString(), textTypeCar.getText().toString(), startTextLocation.getText().toString(), endTextLocation.getText().toString(), textCalDis.getText().toString()
                , textCalDu.getText().toString(), textTypeOil.getText().toString(), calOil.getText().toString(), calMoney.getText().toString(), str_date, str_average_baht);
        if (save <= 0) {

        }
    }
}
