package com.sm.cmdss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ActPagination extends AppCompatActivity {
    private ListView listview;
    private TextView title;

    private ArrayList<String> data;
    ArrayAdapter<String> sd;


    public int TOTAL_LIST_ITEMS = 1030;
    public int NUM_ITEMS_PAGE = 100;
    private int noOfBtns;
    private Button[] btns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pagination);

        listview = (ListView) findViewById(R.id.list);
        title = (TextView) findViewById(R.id.title);

        Btnfooter();

        data = new ArrayList<String>();

        /*
         * The ArrayList data contains all the list items
         */
        for (int i = 0; i < TOTAL_LIST_ITEMS; i++) {
            data.add("This is Item " + (i + 1));
        }

        loadList(0);

        CheckBtnBackGroud(0);
    }

    private void Btnfooter() {
        int val = TOTAL_LIST_ITEMS % NUM_ITEMS_PAGE;
        val = val == 0 ? 0 : 1;
        noOfBtns = TOTAL_LIST_ITEMS / NUM_ITEMS_PAGE + val;

        LinearLayout ll = (LinearLayout) findViewById(R.id.btnLay);

        btns = new Button[noOfBtns];

        for (int i = 0; i < noOfBtns; i++) {
            btns[i] = new Button(this);
            btns[i].setBackgroundColor(getResources().getColor(android.R.color.transparent));
            btns[i].setText("" + (i + 1));

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(btns[i], lp);

            final int j = i;
            btns[j].setOnClickListener(new View.OnClickListener() {

                public void onClick(View argView) {
                    loadList(j);
                    CheckBtnBackGroud(j);
                }
            });
        }

    }

    /**
     * Method for Checking Button Backgrounds
     */
    private void CheckBtnBackGroud(int index) {
        title.setText("Page " + (index + 1) + " of " + noOfBtns);
        for (int i = 0; i < noOfBtns; i++) {
            if (i == index) {
                btns[index].setBackgroundDrawable(getResources().getDrawable(R.drawable.box_green));
                btns[i].setTextColor(getResources().getColor(android.R.color.white));
            } else {
                btns[i].setBackgroundColor(getResources().getColor(android.R.color.transparent));
                btns[i].setTextColor(getResources().getColor(android.R.color.black));
            }
        }

    }

    /**
     * Method for loading data in listview
     *
     * @param number
     */
    private void loadList(int number) {
        ArrayList<String> sort = new ArrayList<String>();

        int start = number * NUM_ITEMS_PAGE;
        for (int i = start; i < (start) + NUM_ITEMS_PAGE; i++) {
            if (i < data.size()) {
                sort.add(data.get(i));
            } else {
                break;
            }
        }
        sd = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                sort);
        listview.setAdapter(sd);
    }
}
