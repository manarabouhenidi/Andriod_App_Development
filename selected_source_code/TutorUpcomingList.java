package com.rough.tuber.tuber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TutorUpcomingList extends AppCompatActivity {
    public String coursekey = "";
    public ListView mainListView;
    public ArrayAdapter<String> listAdapter;
    public static info info;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_upcoming_list);
        mainListView = (ListView) findViewById(R.id.AppointmentList);

        //        String[] Appointments = new String[]{};
        ArrayList<String> appointmentsList = new ArrayList<String>();


//        String[] Appointments = new String[]{};
        final ArrayList<String> appIdsList = new ArrayList<String>();

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, appointmentsList);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("APPOINTMENTS");

        FirebaseDatabase database;
        final DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference("APPOINTMENTS");

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    final String userID = user.getUid().toString();

                    AppInfo in = data.getValue(AppInfo.class);

                    String tutorID = data.child("tutorId").getValue(String.class);
                    Boolean se = data.child("isApproved").getValue(Boolean.class);
                    Boolean se2 = data.child("isChecked").getValue(Boolean.class);

                    if (in != null && se != null && (se == true) && (se2 == true) && tutorID.equals(userID)) {

                        listAdapter.add(/*"Appointment with: " + in.tutorName + "\n" + */
                                "Appointment On: " + in.appDate + "\n" + "From: " + in.startTime + "  To: " + in.endTime + "\n" +
                                        "Reason for Appiontment: " + in.appDiscrip);
                        appIdsList.add(data.getKey());
                    }
                }
                mainListView.setAdapter(listAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //   String tutorName = (mainListView.getItemAtPosition(position)).toString();
                String appID = appIdsList.get(position);

                Intent selectedApp = new Intent(TutorUpcomingList.this, MoveAppiontmentToPast.class);

                //     selectedApp.putExtra("tutorName", tutorName);
                selectedApp.putExtra("appID", appID);

                TutorUpcomingList.this.startActivity(selectedApp);


            }
        });


    }

}