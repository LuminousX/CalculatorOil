package com.example.hispeed.calculatoroil;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hispeed.calculatoroil.Adapter.AdapterRecyclerView;
import com.example.hispeed.calculatoroil.Models.DatabaseOil;
import com.example.hispeed.calculatoroil.Models.DetailRecyclerView;
import com.example.hispeed.calculatoroil.Models.RecyclerTouchListener;
import com.example.hispeed.calculatoroil.ViewHolders.ClickListener;

import java.util.ArrayList;

public class HistoryFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    public HistoryFragment() {
    }

    DatabaseOil databaseOil;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;

    String name, type_car, origin, destination, distance, duration, type_oil, spend_oil, money, str_date, average_baht;

    TextView text_no_history;

    RecyclerView recyclerView;
    AdapterRecyclerView adapterRecyclerView;
    ArrayList<DetailRecyclerView> detailRecyclerViews = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("ประวัติการเดินทาง");
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);

        bindView();

        databaseOil = new DatabaseOil(getActivity());
        sqLiteDatabase = databaseOil.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + databaseOil.TABLE_NAME, null);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapterRecyclerView = new AdapterRecyclerView(getActivity(), detailRecyclerViews);

        retrieve();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, final int position) {

            }
        }));

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, final int direction) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogStyle);
                builder.setTitle("ลบข้อมูลการเดินทาง")
                        .setMessage("คุณต้องการลบข้อมูลการเดินทางนี้หรือไม่?")
                        .setIcon(R.drawable.ic_warning)
                        .setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (cursor != null && cursor.moveToPosition(viewHolder.getAdapterPosition())) {
                                    name = cursor.getString(cursor.getColumnIndex(DatabaseOil.COL_NAME));
                                    type_car = cursor.getString(cursor.getColumnIndex(DatabaseOil.COL_TYPE_CAR));
                                    origin = cursor.getString(cursor.getColumnIndex(DatabaseOil.COL_ORIGIN));
                                    destination = cursor.getString(cursor.getColumnIndex(DatabaseOil.COL_DESTINATION));
                                    distance = cursor.getString(cursor.getColumnIndex(DatabaseOil.COL_DISTANCE));
                                    duration = cursor.getString(cursor.getColumnIndex(DatabaseOil.COL_DURATION));
                                    type_oil = cursor.getString(cursor.getColumnIndex(DatabaseOil.COL_TYPE_OIL));
                                    spend_oil = cursor.getString(cursor.getColumnIndex(DatabaseOil.COL_SPEND_OIL));
                                    money = cursor.getString(cursor.getColumnIndex(DatabaseOil.COL_MONEY));
                                    str_date = cursor.getString(cursor.getColumnIndex(DatabaseOil.COL_DATE));
                                    average_baht = cursor.getString(cursor.getColumnIndex(DatabaseOil.COL_AVERAGE_BAHT));

                                    sqLiteDatabase.execSQL("DELETE FROM " + DatabaseOil.TABLE_NAME + " WHERE " + DatabaseOil.COL_NAME + "='" + name + "' AND "
                                            + DatabaseOil.COL_TYPE_CAR + "='" + type_car + "' AND " + DatabaseOil.COL_ORIGIN + "='" + origin + "' AND "
                                            + DatabaseOil.COL_DESTINATION + "='" + destination + "' AND " + DatabaseOil.COL_DISTANCE + "='" + distance + "' AND "
                                            + DatabaseOil.COL_DURATION + "='" + duration + "' AND " + DatabaseOil.COL_TYPE_OIL + "='" + type_oil + "' AND "
                                            + DatabaseOil.COL_SPEND_OIL + "='" + spend_oil + "' AND " + DatabaseOil.COL_MONEY + "='" + money + "' AND "
                                            + DatabaseOil.COL_DATE + "='" + str_date + "' AND " + DatabaseOil.COL_AVERAGE_BAHT + "='" + average_baht + "';");

                                    cursor.requery();
                                }
                                databaseOil.close();

                                int position = viewHolder.getAdapterPosition();
                                detailRecyclerViews.remove(position);
                                adapterRecyclerView.notifyDataSetChanged();

                                if (detailRecyclerViews.size() < 1) {
                                    text_no_history.setVisibility(View.VISIBLE);
                                }
                            }
                        })
                        .setNegativeButton("ไม่ใช่", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                                final int position = viewHolder.getAdapterPosition();
                                adapterRecyclerView.notifyItemChanged(position);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onStop() {
        super.onStop();
        sqLiteDatabase.close();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    private void bindView() {
        text_no_history = (TextView) getActivity().findViewById(R.id.text_no_history);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView_list);
    }

    public void retrieve() {
        DatabaseOil databaseOils = new DatabaseOil(getActivity());

        sqLiteDatabase = databaseOils.getWritableDatabase();

        detailRecyclerViews.clear();

        cursor = databaseOils.getAllDetailRecyclerView();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            String nameRV = cursor.getString(0);
            String type_carRV = cursor.getString(1);
            String originRV = cursor.getString(2);
            String destinationRV = cursor.getString(3);
            String distanceRV = cursor.getString(4);
            String durationRV = cursor.getString(5);
            String type_oilRV = cursor.getString(6);
            String spend_oilRV = cursor.getString(7);
            String moneyRV = cursor.getString(8);
            String str_dateRV = cursor.getString(9);
            String average_bahtRV = cursor.getString(10);

            DetailRecyclerView detailRecyclerView = new DetailRecyclerView(nameRV, type_carRV, originRV, destinationRV, distanceRV, durationRV, type_oilRV, spend_oilRV, moneyRV, str_dateRV, average_bahtRV);
            detailRecyclerViews.add(detailRecyclerView);
            cursor.moveToNext();
        }
        if (!(detailRecyclerViews.size() < 1)) {

            recyclerView.setAdapter(adapterRecyclerView);
        }
        //   cursor.close();
        //    databaseOil.close();
        if (detailRecyclerViews.size() < 1) {
            text_no_history.setVisibility(View.VISIBLE);

        }
        if (detailRecyclerViews.isEmpty()) {
            recyclerView.setVisibility(View.INVISIBLE);
        }

    }
}
