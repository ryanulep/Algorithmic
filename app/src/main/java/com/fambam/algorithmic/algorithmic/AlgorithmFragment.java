package com.fambam.algorithmic.algorithmic;


import android.os.Bundle;
import android.app.Fragment;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlgorithmFragment extends Fragment {

    private int[] imageIds;
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
        // String orderKey = getString(R.string.ordering);
        text = (TextView)v.findViewById(R.id.txtxt);        // Used for debugging
        int[] drawableIds = null;
        int[] ordering = null;
        Bundle bundle = getArguments();

        // Retrieve values from bundle if successfully delivered
        if (bundle != null) {
            str = "asdf";
            algorithm = bundle.getParcelable(algoKey);
            drawableIds = bundle.getIntArray(drawKey);
            // ordering = bundle.getIntArray(orderKey);
        }

        /*

        //Construct the ImageViews from passed in drawableIds and add them to the ConstraintView
        ConstraintLayout baseLayout = getActivity().findViewById(R.id.algorithm_fragment);

        this.imageIds = new int[drawableIds.length];

        for (int i = 0; i < this.imageIds.length; i++) {
            this.imageIds[i] = 100+i;
            ImageView image = new ImageView(getActivity());
            image.setId(this.imageIds[i]);
            image.setImageResource(drawableIds[i]);
            baseLayout.addView(image);
        }
        ConstraintSet set = new ConstraintSet();
        set.clone(baseLayout);
        ConstraintSet initSet = this.algorithm.initialize(set, imageIds, ordering);
        initSet.applyTo(baseLayout);

        */

        return v;
    }

    public void updateTextView(String param) {
        text.setText(param);
    }

    public void updateTextView(){
        text.setText(str);
    }

}
