package com.fambam.algorithmic.algorithmic;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AlgorithmActivity extends AppCompatActivity {
    private Algorithm algorithm;
    private AlgorithmFragment algoFragment;
    private ExplanationFragment explainFragment;
    private UpdateOrdering updateOrdering;
    private Button backBtn;
    private Button nextBtn;
    private static final int ALGO = 0;
    private static final int EXPLAIN = 1;
    private static final int BOTH = 2;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference dRef = database.getReference();
    String flags = "0000000000000000";
    String flagsUpdate = "0000000000000000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);

        backBtn = findViewById(R.id.btn_back);
        nextBtn = findViewById(R.id.btn_next);

        //Get the drawable identifiers from the intent
        String algoKey = getString(R.string.algo_key);
        String drawKey = getString(R.string.drawables);
        String dataKey = getString(R.string.data);
        String orderKey = getString(R.string.ordering);
        String assetKey = getString(R.string.asset);

        Intent callingIntent = getIntent();
        this.algorithm = callingIntent.getParcelableExtra(algoKey);
        int[] drawableIds = callingIntent.getIntArrayExtra(drawKey);
        int[] data = callingIntent.getIntArrayExtra(dataKey);
        this.updateOrdering = callingIntent.getParcelableExtra(orderKey);
        AlgorithmAssets asset = callingIntent.getParcelableExtra(assetKey);

        // Passing data to algorithm fragment through the Bundle() class
        Bundle algorithmBundle = new Bundle();
        algorithmBundle.putParcelable(algoKey, (Parcelable) this.algorithm);
        algorithmBundle.putIntArray(drawKey, drawableIds);
        algorithmBundle.putIntArray(dataKey, data);
        algoFragment = new AlgorithmFragment();
        algoFragment.setArguments(algorithmBundle);

        // Passing data to explanation fragment through the Bundle() class
        Bundle explanationBundle = new Bundle();
        explanationBundle.putParcelable(assetKey, asset);
        explainFragment = new ExplanationFragment();
        explainFragment.setArguments(explanationBundle);

        // Adding fragments to activity
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.algorithm_fragment, algoFragment);
        ft.add(R.id.explanation_fragment, explainFragment);
        ft.commit();

        if (updateOrdering.getCurrentStep() == 0) {
            backBtn.setEnabled(false);
        }

        //For database
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    flags = dataSnapshot.child(UID).getValue(userData.class).getFlags();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Could not read value
                // User logger??
            }
        });
    }

    public void swap(View view) {
        int option = updateOrdering.next();
        if (option == ALGO) {
            algoFragment.swap();
        }
        else if (option == EXPLAIN) {
            explainFragment.explain();
        }
        else if (option == BOTH) {
            explainFragment.explain();
            algoFragment.swap();
        }

        // Restricting back button presses for user
        if (updateOrdering.getCurrentStep() > 0) {
            backBtn.setEnabled(true);
        }
        // Restricting next button presses for user when reaching last move
        if (updateOrdering.getCurrentStep() == updateOrdering.getUpdateOrderSize()) {
            nextBtn.setEnabled(false);
<<<<<<< HEAD
=======

            // Database flags update. Highlights button when explanation is complete.
            if(algorithm.getID() == 1){
                flagsUpdate = flags.substring(0,1)+'1'+flags.substring(2, 16);
                dRef.child(UID).child("flags").setValue(flagsUpdate);
            }
            else if(algorithm.getID() == 2){
                flagsUpdate = flags.substring(0,5)+'1'+flags.substring(6, 16);
                dRef.child(UID).child("flags").setValue(flagsUpdate);
            }
            else if(algorithm.getID() == 3){
                flagsUpdate = flags.substring(0,9)+'1'+flags.substring(10, 16);
                dRef.child(UID).child("flags").setValue(flagsUpdate);
            }
            else if(algorithm.getID() == 4){
                flagsUpdate = flags.substring(0,13)+'1'+flags.substring(14, 16);
                dRef.child(UID).child("flags").setValue(flagsUpdate);
            }

>>>>>>> 6a99e3a70cb6d893176e454039233ce74d65c704
        }
    }

    public void back(View view) {
        int option = updateOrdering.back();
        if (option == ALGO) {
            algoFragment.back();
        }
        else if (option == EXPLAIN) {
            explainFragment.back();
        }
        else if (option == BOTH) {
            algoFragment.back();
            explainFragment.back();
        }

        // Not allowing user to press back button in the beginning
        if (updateOrdering.getCurrentStep() == 0) {
            backBtn.setEnabled(false);
        }
        // Allowing user to press next button as long as not at the end
        if (updateOrdering.getCurrentStep() < updateOrdering.getUpdateOrderSize()) {
            nextBtn.setEnabled(true);
        }
    }
}
