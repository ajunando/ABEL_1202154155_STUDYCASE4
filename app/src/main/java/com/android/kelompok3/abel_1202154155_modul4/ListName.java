package com.android.kelompok3.abel_1202154155_modul4;

import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class ListName extends AppCompatActivity {

    private ListView mListView;
    private ProgressBar mProgressBar;
    private String [] mUsers= {
            "Nabila","Abiyoga","Tsara","Aziz","Fiqih","Ajunando",
            "Dikka","Amri","Aditya"

    };

    private AddItemToListView mAddItemToListView;
    private Button mStartAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_name);

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mListView = (ListView) findViewById(R.id.listView);
        mStartAsyncTask = (Button) findViewById(R.id.button_startAsyncTask);

        /** make progressbar visible when app running
         */
        mListView.setVisibility(View.GONE);

        /**
         * setup adapter
         */
        mListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new ArrayList<String>()));


        /**
         * start asyntask after button clicked
         */
        mStartAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //process adapter with asyncTask
                mAddItemToListView = new AddItemToListView();
                mAddItemToListView.execute();
            }
        });
    }


     //Untuk memasukkan proses asyntask ke dalam sebuah kelas

    public class AddItemToListView extends AsyncTask<Void, String, Void> {
        int count;
        private ArrayAdapter<String> mAdapter;
        private int counter=1;
        ProgressDialog mProgressDialog = new ProgressDialog(ListName.this);

        @Override
        protected void onPreExecute() {
            ////mengambil adapter dari array tersebut
            mAdapter = (ArrayAdapter<String>) mListView.getAdapter();

            //Untuk Progress Jalannya dialog
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            //mengeset judul progress dialog
            mProgressDialog.setTitle("Loading Data");
            mProgressDialog.setMessage("Please wait....");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgress(0);
            //menampilkan progress dialog
            mProgressDialog.show();
            count = 0;
        }

        @Override
        protected Void doInBackground(Void... params) {
            //membuat perulangan untuk memunculkan nama mahasiswa
            for (String item : mUsers){
                publishProgress(item);
                try {
                    Thread.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(isCancelled()){
                    mAddItemToListView.cancel(true);
                }
            }
            return null;
        }

        @Override

        protected void onProgressUpdate(String... values) {
            //memulai dari array 0
            mAdapter.add(values[0]);
            //hitungan pada saat progress update bertambah
            counter++;
            //mengeset hitungan di dalam progress dialog
            mProgressDialog.setProgress(count);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //menampilkan nilai dari return yang ada di method doInBackground
            mProgressBar.setVisibility(View.GONE);

            //remove progress dialog
            mProgressDialog.dismiss();
            mListView.setVisibility(View.VISIBLE);


        }
    }
}


