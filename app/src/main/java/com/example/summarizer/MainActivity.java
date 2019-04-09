package com.example.summarizer;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE=43;

    TextView uriText,path;
    TextView print;
    Button btn_filePicker;
    Intent myFileIntent;
    Button b;

    String linkm;
    String x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uriText=(TextView)findViewById(R.id.uri);
        path=(TextView)findViewById(R.id.path);
        btn_filePicker=(Button)findViewById(R.id.btn_filePicker);
        b = (Button)findViewById(R.id.button);
        print = (TextView)findViewById(R.id.text);
        btn_filePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                myFileIntent=new Intent(Intent.ACTION_GET_CONTENT);
//                myFileIntent.setType("*/*");
//                startActivityForResult(myFileIntent, 10);

                startSarch();
            }
        });


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finaloutput(x);
            }
        });
    }

    private void startSarch() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("text/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        switch (requestCode){
//            case 10:
//
//                    if (resultCode == RESULT_OK) {
//                        String path = data.getData().getPath();
//                        txt_pathShow.setText(path);
//                        linkm=path;
//                    }
//
//
//                break;
//        }

        super.onActivityResult(requestCode,requestCode,data);

        if(requestCode==REQUEST_CODE && resultCode== Activity.RESULT_OK){

            if(data!=null){

                Uri uri=data.getData();

                Toast.makeText(this,"Uri :"+uri,Toast.LENGTH_LONG).show();
                uriText.setText(uri.toString());
                Toast.makeText(this,"Path :"+uri.getPath(),Toast.LENGTH_LONG).show();
                path.setText(uri.getPath().toString());

                x=uriText.getText().toString();
            }

        }
    }


    public void finaloutput(String x){

        try {


//            MainActivity main = new MainActivity();
//            String x = main.linkm;
            Log.d("1","maiin object:  "+x);
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
        catch (Exception  e){

            Toast.makeText(this, " "+e, Toast.LENGTH_LONG).show();
        }



    }

}

