package com.example.summarizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView print;
    TextView txt_pathShow;
    Button btn_filePicker;
    Intent myFileIntent;
    Button b;

    String linkm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_pathShow=(TextView)findViewById(R.id.txt_path);
        btn_filePicker=(Button)findViewById(R.id.btn_filePicker);
        b = (Button)findViewById(R.id.button);
        print = (TextView)findViewById(R.id.text);
        btn_filePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFileIntent=new Intent(Intent.ACTION_GET_CONTENT);
                myFileIntent.setType("*/*");
                startActivityForResult(myFileIntent, 10);
            }
        });

        linkm = txt_pathShow.getText().toString();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finaloutput();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 10:
                if(resultCode==RESULT_OK){
                    String path=data.getData().getPath();
                    txt_pathShow.setText(path);
                }
                break;
        }
    }


    public void finaloutput(){
        MainActivity main=new MainActivity();
        String x=main.linkm;
        SummaryTool summary = new SummaryTool();
        summary.inputlink(x);
        summary.init();
        summary.extractSentenceFromContext();
        summary.groupSentencesIntoParagraphs();
        //summary.printSentences();
        summary.createIntersectionMatrix();

        //System.out.println("INTERSECTION MATRIX");
        //summary.printIntersectionMatrix();

        summary.createDictionary();
        //summary.printDicationary();

//        System.out.println("SUMMMARY:");
//        System.out.println();
          summary.createSummary();
          summary.printSummary();

          // summary.printStats();

        print.setText(summary.a);


    }

}

