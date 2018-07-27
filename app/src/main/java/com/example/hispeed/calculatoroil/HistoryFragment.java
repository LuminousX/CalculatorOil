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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hispeed.calculatoroil.Adapter.RecyclerViewAdapter;
import com.example.hispeed.calculatoroil.Databases.DatabaseOil;
import com.example.hispeed.calculatoroil.Adapter.DetailRecyclerView;

import java.util.ArrayList;

public class HistoryFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    public HistoryFragment() {
    }

    private DatabaseOil databaseOil;
    private SQLiteDatabase sqLiteDatabase;
    private Cursor cursor;

    private String name, type_car, origin, destination, distance, duration, type_oil, spend_oil, money, str_date, average_baht;

    private TextView textNoHistory;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<DetailRecyclerView> detailRecyclerViews = new ArrayList<>();

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

        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), detailRecyclerViews);

        retrieve();

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
                                recyclerViewAdapter.notifyDataSetChanged();

                                if (detailRecyclerViews.size() < 1) {
                                    textNoHistory.setVisibility(View.VISIBLE);
                                }
                            }
                        })
                        .setNegativeButton("ไม่ใช่", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                                final int position = viewHolder.getAdapterPosition();
                                recyclerViewAdapter.notifyItemChanged(position);
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
        textNoHistory = (TextView) getActivity().findViewById(R.id.text_no_history);
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
            String typeCarRV = cursor.getString(1);
            String originRV = cursor.getString(2);
            String destinationRV = cursor.getString(3);
            String distanceRV = cursor.getString(4);
            String durationRV = cursor.getString(5);
            String typeOilRV = cursor.getString(6);
            String spendOilRV = cursor.getString(7);
            String moneyRV = cursor.getString(8);
            String dateRV = cursor.getString(9);
            String averageBahtRV = cursor.getString(10);

            DetailRecyclerView detailRecyclerView = new DetailRecyclerView(nameRV, typeCarRV, originRV, destinationRV, distanceRV, durationRV, typeOilRV, spendOilRV, moneyRV, dateRV, averageBahtRV);
            detailRecyclerViews.add(detailRecyclerView);
            cursor.moveToNext();
        }
        if (!(detailRecyclerViews.size() < 1)) {
            recyclerView.setAdapter(recyclerViewAdapter);
        }

      //  cursor.close();
       // databaseOil.close();

        if (detailRecyclerViews.size() < 1) {
            textNoHistory.setVisibility(View.VISIBLE);

        }
        if (detailRecyclerViews.isEmpty()) {
            recyclerView.setVisibility(View.INVISIBLE);
        }
    }
}
