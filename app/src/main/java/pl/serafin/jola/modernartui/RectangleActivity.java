package pl.serafin.jola.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.TimeZoneFormat;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class RectangleActivity extends AppCompatActivity {

    SeekBar seekBar;
    ImageView rectangle1, rectangle2, rectangle3, rectangle4, rectangle5;
    DialogFragment mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rectangle);

        seekBar = findViewById(R.id.seekBar);
        rectangle1 = findViewById(R.id.rectimage1);
        rectangle2 = findViewById(R.id.rectimage2);
        rectangle3 = findViewById(R.id.rectimage3);
        rectangle4 = findViewById(R.id.rectimage4);
        rectangle5 = findViewById(R.id.rectimage5);

        rectangle1.setBackgroundColor(Color.rgb(102,102,255));
        rectangle2.setBackgroundColor(Color.rgb(204,0,204));
        rectangle3.setBackgroundColor(Color.rgb(153,0,0));
        rectangle4.setBackgroundColor(Color.rgb(255,250,255));
        rectangle5.setBackgroundColor(Color.rgb(0,0,102));
        seekBar.setOnSeekBarChangeListener(changeSeekBar);
    }

    SeekBar.OnSeekBarChangeListener changeSeekBar = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            rectangle1.setBackgroundColor(Color.rgb(102 + i,102 + i,255 - i));
            rectangle2.setBackgroundColor(Color.rgb(204 + i/2 ,i*2,204 - i/2));
            rectangle3.setBackgroundColor(Color.rgb(153,i,0));
            rectangle5.setBackgroundColor(Color.rgb(0,i,102));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.information:
                showDialogFragment();
                return true;
        }

        return false;
    }

    private void showDialogFragment()
    {
        mDialog = InformationDialogFragment.newInstance();
        mDialog.show(getFragmentManager(), "Question");
    }

    public static class InformationDialogFragment extends DialogFragment
    {
        public static InformationDialogFragment newInstance()
        {
          return new InformationDialogFragment();
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            TextView title =  new TextView(getActivity().getApplicationContext());
            title.setText("Inspirated by the works of artists such as Piet Mondrian and Ben Nicholson.");
            title.setGravity(Gravity.CENTER);
            title.setTextSize(14);
            title.setTextColor(Color.BLACK);
           // dialog2.setCustomTitle(title);

            return new AlertDialog.Builder(getActivity())
                    .setCustomTitle(title)
                    .setMessage("Click below to learn more!")
                    .setPositiveButton("Visit MOMA", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
                            startActivity(browserIntent);
                            
                        }
                    })
                    .setNegativeButton("Not Now", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                        }
                    })
                    .setCancelable(false)
                    .create();

        }
    }

}
