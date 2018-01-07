package com.example.hispeed.calculatoroil;


import android.app.Activity;
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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class CalculateOil extends Fragment implements AdapterView.OnItemSelectedListener {

    public CalculateOil() {

    }

    private Button btncal;
    private TextView textsave;
    String[] arrayOil = {"ประเภทน้ำมัน", "แก๊สโซฮอล 95", "แก๊สโซฮอล 91", "แก๊สโซฮอล E20", "แก๊สโซฮอล E85", "ดีเซล", "เบนซิน 95"};
    String[] type_null = {"รุ่น"};

    private Spinner spinType;
    private Spinner spinOil;
    private Spinner spinType2;


    private String distanceStringIntent;
    private String durationStringIntent;

    private String distanceTextIntent;
    private String durationTextIntent;
    private String startLocation;
    private String endLocation;

    private TextView textCalDis;
    private TextView textCalDu;
    private TextView startTextLocation;
    private TextView endTextLocation;
    private TextView textTypeOil;
    private TextView textNameCar;
    private TextView calOil;
    private TextView calMoney;
    private TextView textTypeCar;

    private TextView text_date;

    private TextView text_money_oil_baht;
    private TextView average_baht;

    private double calAmountOil;
    private double calSpentOil;
    private double distanceInDouble;

    private String str_date;

    public DetailCar detailCar = new DetailCar();

    public RadioButton radioTown, radioCountrySide, radioCombined;
    public int point;

    public EditText edt_oil_price;
    public ImageView image_search_oil_price;

    double oil_price_search;
    double average_double_bath;

    public String str_average_baht;

    RelativeLayout relativeLayout, relative_text_save;

    String distanceText;
    String calTextOil;

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
        return inflater.inflate(R.layout.activity_calculate_oil, container, false);
    }


    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);

        spinType = (Spinner) getActivity().findViewById(R.id.spinTpye);
        spinOil = (Spinner) getActivity().findViewById(R.id.spinOil);
        spinType2 = (Spinner) getActivity().findViewById(R.id.spinType2);

        relativeLayout = (RelativeLayout) getActivity().findViewById(R.id.relativeOil2);

        radioTown = (RadioButton) getActivity().findViewById(R.id.radioTown);
        radioCountrySide = (RadioButton) getActivity().findViewById(R.id.radopCountrySide);
        radioCombined = (RadioButton) getActivity().findViewById(R.id.radioCombined);


        relative_text_save = (RelativeLayout) getActivity().findViewById(R.id.relative_text_save);

        textTypeOil = (TextView) getActivity().findViewById(R.id.textTypeOil);
        textCalDis = (TextView) getActivity().findViewById(R.id.distanceTextOil);
        textCalDu = (TextView) getActivity().findViewById(R.id.durationTextOil);
        startTextLocation = (TextView) getActivity().findViewById(R.id.startTextLocation);
        endTextLocation = (TextView) getActivity().findViewById(R.id.endTextLocation);
        textNameCar = (TextView) getActivity().findViewById(R.id.textNameCar);
        calOil = (TextView) getActivity().findViewById(R.id.calOil);
        calMoney = (TextView) getActivity().findViewById(R.id.calMoney);
        textTypeCar = (TextView) getActivity().findViewById(R.id.textTypeCar);

        text_money_oil_baht = (TextView) getActivity().findViewById(R.id.text_money_oil_baht);
        average_baht = (TextView) getActivity().findViewById(R.id.average);

        text_date = (TextView) getActivity().findViewById(R.id.text_date);

        edt_oil_price = (EditText) getActivity().findViewById(R.id.edt_oil_price);
        image_search_oil_price = (ImageView) getActivity().findViewById(R.id.image_search_oil_price);

        final ArrayAdapter<String> adapterType = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, detailCar.type_car);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinType.setAdapter(adapterType);

        final ArrayAdapter<String> adapterOil = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, arrayOil);
        adapterOil.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinOil.setAdapter(adapterOil);


        spinType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectTypeCar();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btncal = (Button) getActivity().findViewById(R.id.btncal);
        btncal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                    oil_price_search = Double.parseDouble(edt_oil_price.getText().toString());
                    relativeLayout.setVisibility(View.VISIBLE);
                    Animation animationRelative = AnimationUtils.loadAnimation(getActivity(), R.anim.transition_save);
                    animationRelative.reset();
                    relativeLayout.clearAnimation();
                    relativeLayout.setAnimation(animationRelative);

                    distanceText = String.format("%.1f", Double.parseDouble(distanceStringIntent) / 1000);
                    double distanceDouble = Double.parseDouble(distanceText);
                    DecimalFormat decimalFormat = new DecimalFormat("#,###.0");
                    String distanceString = decimalFormat.format(distanceDouble);

                    textCalDis.setText(distanceString + " km.");

                    textCalDu.setText(durationTextIntent);
                    startTextLocation.setText(startLocation);
                    endTextLocation.setText(endLocation);
                    textNameCar.setText(spinType.getSelectedItem().toString());
                    textTypeCar.setText(spinType2.getSelectedItem().toString());
                    textTypeOil.setText(spinOil.getSelectedItem().toString() + " (" + oil_price_search + " บาท)");

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC+7"));

                    str_date = simpleDateFormat.format(new Date());

                    checkPriceOil();

                    String calMoneyText = String.format("%.2f", calSpentOil);
                    double calMoneyDouble = Double.parseDouble(calMoneyText);
                    DecimalFormat decimalFormatOil = new DecimalFormat("#,###.00");
                    String calMoneyString = decimalFormatOil.format(calMoneyDouble);

                    calOil.setText(String.format("%.2f", calAmountOil) + " L.");
                    calMoney.setText(calMoneyString + " บาท");


                    average_baht.setText(String.format("%.2f", calSpentOil / distanceInDouble) + " บาท ต่อ 1 km.");
                    str_average_baht = average_baht.getText().toString();

                    relative_text_save.setVisibility(View.VISIBLE);
                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.transition_save);
                    animation.reset();
                    relative_text_save.clearAnimation();
                    relative_text_save.setAnimation(animation);

//                    Snackbar.make(view,"",Snackbar.LENGTH_INDEFINITE).setAction("บันทึก", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            CheckData();
//                        }
//                    }).show();
                    edt_oil_price.onEditorAction(EditorInfo.IME_ACTION_DONE);

                }
            }
        });

        textsave = (TextView) getActivity().findViewById(R.id.textSave);
        textsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckData();
            }
        });

        carTypeNull();

        image_search_oil_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = intentOilPrice(getActivity());
                startActivity(intent);
            }
        });

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

    public void CheckData() {

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

                    History history = new History();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.enter_right, R.anim.exit_left, R.anim.enter_left, R.anim.exit_right);
                    fragmentTransaction.replace(R.id.fragment_continer, history);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

    }

    private boolean saveData() {
        final DatabaseOil databaseOil = new DatabaseOil(getActivity());

        long save = databaseOil.InsertData(textNameCar.getText().toString(), textTypeCar.getText().toString(), startTextLocation.getText().toString(), endTextLocation.getText().toString(), textCalDis.getText().toString()
                , textCalDu.getText().toString(), textTypeOil.getText().toString(), calOil.getText().toString(), calMoney.getText().toString(), str_date, str_average_baht);
        if (save <= 0) {

        }
        return true;
    }


    public void selectTypeCar() {

        switch ((int) spinType.getSelectedItemId()) {
            case 0:
                carTypeNull();
                break;
            case 1:
                ArrayAdapter<String> adapterBmw = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, detailCar.bmw);
                adapterBmw.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinType2.setAdapter(adapterBmw);
                break;

            case 2:
                ArrayAdapter<String> adapterChevrolet = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, detailCar.chevrolet);
                adapterChevrolet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinType2.setAdapter(adapterChevrolet);
                break;

            case 3:
                ArrayAdapter<String> adapterFord = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, detailCar.ford);
                adapterFord.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinType2.setAdapter(adapterFord);
                break;

            case 4:
                ArrayAdapter<String> adapterFoton = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, detailCar.foton);
                adapterFoton.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinType2.setAdapter(adapterFoton);
                break;

            case 5:
                ArrayAdapter<String> adapterHonda = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, detailCar.honda);
                adapterHonda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinType2.setAdapter(adapterHonda);
                break;

            case 6:
                ArrayAdapter<String> adapterIsuzu = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, detailCar.isuzu);
                adapterIsuzu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinType2.setAdapter(adapterIsuzu);
                break;

            case 7:
                ArrayAdapter<String> adapterMazda = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, detailCar.mazda);
                adapterMazda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinType2.setAdapter(adapterMazda);
                break;

            case 8:
                ArrayAdapter<String> adapterMercedes_benz = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, detailCar.mercedes_benz);
                adapterMercedes_benz.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinType2.setAdapter(adapterMercedes_benz);
                break;

            case 9:
                ArrayAdapter<String> adapterMg = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, detailCar.mg);
                adapterMg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinType2.setAdapter(adapterMg);
                break;

            case 10:
                ArrayAdapter<String> adapterMini = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, detailCar.mini);
                adapterMini.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinType2.setAdapter(adapterMini);
                break;

            case 11:
                ArrayAdapter<String> adapterMitsubishi = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, detailCar.mitsubishi);
                adapterMitsubishi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinType2.setAdapter(adapterMitsubishi);
                break;

            case 12:
                ArrayAdapter<String> adapterNissan = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, detailCar.nissan);
                adapterNissan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinType2.setAdapter(adapterNissan);
                break;

            case 13:
                ArrayAdapter<String> adapterSuzuki = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, detailCar.suzuki);
                adapterSuzuki.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinType2.setAdapter(adapterSuzuki);
                break;

            case 14:
                ArrayAdapter<String> adapterTata = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, detailCar.tata);
                adapterTata.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinType2.setAdapter(adapterTata);
                break;

            case 15:
                ArrayAdapter<String> adapterToyota = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, detailCar.toyota);
                adapterToyota.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinType2.setAdapter(adapterToyota);
                break;
        }
    }

    public void checkRadioButton() {
        if (radioTown.isChecked()) {
            switch ((int) spinType.getSelectedItemId()) {
                case 1:
                    calAmountOil = distanceInDouble / detailCar.bmw_town[point];
                    break;
                case 2:
                    calAmountOil = distanceInDouble / detailCar.chevrolet_town[point];
                    break;
                case 3:
                    calAmountOil = distanceInDouble / detailCar.ford_town[point];
                    break;
                case 4:
                    calAmountOil = distanceInDouble / detailCar.foton_town[point];
                    break;
                case 5:
                    calAmountOil = distanceInDouble / detailCar.honda_town[point];
                    break;
                case 6:
                    calAmountOil = distanceInDouble / detailCar.isuzu_town[point];
                    break;
                case 7:
                    calAmountOil = distanceInDouble / detailCar.mazda_town[point];
                    break;
                case 8:
                    calAmountOil = distanceInDouble / detailCar.mercedes_benz_town[point];
                    break;
                case 9:
                    calAmountOil = distanceInDouble / detailCar.mg_town[point];
                    break;
                case 10:
                    calAmountOil = distanceInDouble / detailCar.mini_town[point];
                    break;
                case 11:
                    calAmountOil = distanceInDouble / detailCar.mitsubishi_town[point];
                    break;
                case 12:
                    calAmountOil = distanceInDouble / detailCar.nissan_town[point];
                    break;
                case 13:
                    calAmountOil = distanceInDouble / detailCar.suzuki_town[point];
                    break;
                case 14:
                    calAmountOil = distanceInDouble / detailCar.tata_town[point];
                    break;
                case 15:
                    calAmountOil = distanceInDouble / detailCar.toyota_town[point];
                    break;
            }

        } else if (radioCountrySide.isChecked()) {
            switch ((int) spinType.getSelectedItemId()) {
                case 1:
                    calAmountOil = distanceInDouble / detailCar.bmw_countryside[point];
                    break;
                case 2:
                    calAmountOil = distanceInDouble / detailCar.chevrolet_countryside[point];
                    break;
                case 3:
                    calAmountOil = distanceInDouble / detailCar.ford_countryside[point];
                    break;
                case 4:
                    calAmountOil = distanceInDouble / detailCar.foton_countryside[point];
                    break;
                case 5:
                    calAmountOil = distanceInDouble / detailCar.honda_countryside[point];
                    break;
                case 6:
                    calAmountOil = distanceInDouble / detailCar.isuzu_countryside[point];
                    break;
                case 7:
                    calAmountOil = distanceInDouble / detailCar.mazda_countryside[point];
                    break;
                case 8:
                    calAmountOil = distanceInDouble / detailCar.mercedes_benz_countryside[point];
                    break;
                case 9:
                    calAmountOil = distanceInDouble / detailCar.mg_countryside[point];
                    break;
                case 10:
                    calAmountOil = distanceInDouble / detailCar.mini_countryside[point];
                    break;
                case 11:
                    calAmountOil = distanceInDouble / detailCar.mitsubishi_countryside[point];
                    break;
                case 12:
                    calAmountOil = distanceInDouble / detailCar.nissan_countryside[point];
                    break;
                case 13:
                    calAmountOil = distanceInDouble / detailCar.suzuki_countryside[point];
                    break;
                case 14:
                    calAmountOil = distanceInDouble / detailCar.tata_countryside[point];
                    break;
                case 15:
                    calAmountOil = distanceInDouble / detailCar.toyota_countryside[point];
                    break;

            }
        } else if (radioCombined.isChecked()) {
            switch ((int) spinType.getSelectedItemId()) {
                case 1:
                    calAmountOil = distanceInDouble / detailCar.bmw_combined[point];
                    break;
                case 2:
                    calAmountOil = distanceInDouble / detailCar.chevrolet_combined[point];
                    break;
                case 3:
                    calAmountOil = distanceInDouble / detailCar.ford_combined[point];
                    break;
                case 4:
                    calAmountOil = distanceInDouble / detailCar.foton_combined[point];
                    break;
                case 5:
                    calAmountOil = distanceInDouble / detailCar.honda_combined[point];
                    break;
                case 6:
                    calAmountOil = distanceInDouble / detailCar.isuzu_combined[point];
                    break;
                case 7:
                    calAmountOil = distanceInDouble / detailCar.mazda_combined[point];
                    break;
                case 8:
                    calAmountOil = distanceInDouble / detailCar.mercedes_benz_combined[point];
                    break;
                case 9:
                    calAmountOil = distanceInDouble / detailCar.mg_combined[point];
                    break;
                case 10:
                    calAmountOil = distanceInDouble / detailCar.mini_combined[point];
                    break;
                case 11:
                    calAmountOil = distanceInDouble / detailCar.mitsubishi_combined[point];
                    break;
                case 12:
                    calAmountOil = distanceInDouble / detailCar.nissan_combined[point];
                    break;
                case 13:
                    calAmountOil = distanceInDouble / detailCar.suzuki_combined[point];
                    break;
                case 14:
                    calAmountOil = distanceInDouble / detailCar.tata_combined[point];
                    break;
                case 15:
                    calAmountOil = distanceInDouble / detailCar.toyota_combined[point];
                    break;
            }
        }
    }

    public void checkPriceOil() {
        distanceInDouble = Double.parseDouble(distanceText);
        point = spinType2.getSelectedItemPosition();
        priceOil(oil_price_search);
    }

    public Double priceOil(Double price) {
        checkRadioButton();
        String strCalAmountOil = String.format("%.2f", calAmountOil);
        calSpentOil = Double.parseDouble(strCalAmountOil) * price;
        return calSpentOil;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
