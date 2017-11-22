package com.fambam.algorithmic.algorithmic;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Guthrie on 11/11/2017.
 */

public class UpdateOrdering implements Parcelable {
    private int[] updateOrder;
    int currentStep;

    UpdateOrdering(int[] updateOrder) {
        this.updateOrder = updateOrder;
        this.currentStep = 0;
    }

    int next() {
        if (currentStep == updateOrder.length) {
            return 0;
        }
        return updateOrder[currentStep++];
    }

    // Function may be buggy
    int back() {
        --currentStep;
        // Assuming first step of algorithm explanation will always be an update to text
        if (currentStep < 0) {
            currentStep = 0;
            return 0;
        }
        return updateOrder[currentStep];
    }

    int getCurrentStep() { return currentStep; }

    int getUpdateOrderSize() { return updateOrder.length; }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeIntArray(updateOrder);
        out.writeInt(currentStep);
    }

    public static final Parcelable.Creator<UpdateOrdering> CREATOR =
            new Parcelable.Creator<UpdateOrdering>() {
                public UpdateOrdering createFromParcel(Parcel in) {
                    return new UpdateOrdering(in);
                }

                public UpdateOrdering[] newArray(int size) {
                    return new UpdateOrdering[size];
                }
            };

    private UpdateOrdering(Parcel in) {
        updateOrder = in.createIntArray();
        currentStep = in.readInt();

    }
}
