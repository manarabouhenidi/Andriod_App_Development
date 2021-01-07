package com.rough.tuber.tuber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MoveAppiontmentToPast extends AppCompatActivity {
    public static String appID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_appiotment_to_past);


        Intent intt = getIntent();
        Bundle b = intt.getExtras();

        if (b != null) {

            this.appID = (String) b.get("appID");

        }

        final FirebaseDatabase database = FirebaseDatabase.getInstance();


        final Button bBack = (Button) findViewById(R.id.bBack);
        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HomeIntent = new Intent(MoveAppiontmentToPast.this, TutorHome.class);
                MoveAppiontmentToPast.this.startActivity(HomeIntent);

            }
        });

        final Button bMove = (Button) findViewById(R.id.bMove);
        bMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HomeIntent = new Intent(MoveAppiontmentToPast.this, TutorHome.class);
                MoveAppiontmentToPast.this.startActivity(HomeIntent);

                DatabaseReference ref = database.getReference("APPOINTMENTS/" + appID);
                ref.child("isPast").setValue(true);


            }
        });

}
}

