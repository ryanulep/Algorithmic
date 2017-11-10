package com.fambam.algorithmic.algorithmic;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlgorithmFragment extends Fragment {
    private Algorithm algorithm;
    TextView text;
    String str;

    public AlgorithmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onActivityCreated(savedInstanceState);

        // A default view must be instantiated on creation of a Fragment
        View v = inflater.inflate(R.layout.fragment_algorithm, container, false);

        // Keys to retrieve values
        String algoKey = getString(R.string.algo_key);
        String drawKey = getString(R.string.drawables);
        String dataKey = getString(R.string.data);
        text = (TextView)v.findViewById(R.id.txtxt);        // Used for debugging
        int[] drawableIds = null;
        int[] data = null;
        Bundle bundle = getArguments();

        // Retrieve values from bundle if successfully delivered
        if (bundle != null) {
            str = "asdf";
            algorithm = bundle.getParcelable(algoKey);
            drawableIds = bundle.getIntArray(drawKey);
            data = bundle.getIntArray(dataKey);

            // Checking if data from main is passed to Fragment
//            for (int i = 0; i < data.length; i++) {
//                str += Integer.valueOf(data[i]).toString();
//            }
        }

        //Construct the ImageViews from passed in drawableIds and add them to the ConstraintView
        ConstraintLayout baseLayout = v.findViewById(R.id.algorithm_fragment);

        int[] imageIds = new int[drawableIds.length];
        int[] dataIds = new int[data.length];

        for (int i = 0; i < data.length; ++i) {
            dataIds[i] = 200+i;
            TextView tView = new TextView(getActivity());
            tView.setId(dataIds[i]);
            tView.setText(Integer.toString(data[i]));
            tView.setLayoutParams(new ConstraintLayout.LayoutParams(120, 120));
            tView.setGravity(Gravity.CENTER);
            tView.setBackgroundColor(Color.WHITE);
            // baseLayout.addView(tView);
        }

        for (int i = 0; i < imageIds.length; i++) {
            imageIds[i] = 100+i;
            ImageView image = new ImageView(getActivity());
            image.setId(imageIds[i]);
            image.setImageResource(drawableIds[i]);
            baseLayout.addView(image);
        }

//        ConstraintSet set = new ConstraintSet();
//        set.clone(baseLayout);
//        this.algorithm.initialize(set, imageIds, dataIds, data);
//        set.applyTo(baseLayout);

        return v;
    }

    public void updateTextView(String param) {
        text.setText(param);
    }

    public void updateTextView(){
        text.setText(str);
    }

}
