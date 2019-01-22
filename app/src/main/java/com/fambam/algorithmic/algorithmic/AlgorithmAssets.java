package com.fambam.algorithmic.algorithmic;

import android.os.Parcel;
import android.os.Parcelable;

public class AlgorithmAssets implements Parcelable {

  private String explanationFilename;
  private String pythonCodeFilename;
  private String cppCodeFilename;
  private String javaCodeFilename;

  public AlgorithmAssets() {
    explanationFilename = "";
    pythonCodeFilename = "";
    cppCodeFilename = "";
    javaCodeFilename = "";
  }

  public AlgorithmAssets(String exp, String py, String cpp, String java) {
    explanationFilename = exp;
    pythonCodeFilename = py;
    cppCodeFilename = cpp;
    javaCodeFilename = java;
  }

  public String getExplanationFilename() {
    return explanationFilename;
  }

  public void setExplanationFilename(String filename) {
    explanationFilename = filename;
  }

  public void setPythonFilename(String filename) {
    pythonCodeFilename = filename;
  }

  public void setCppFilename(String filename) {
    cppCodeFilename = filename;
  }

  public void setJavaFilename(String filename) {
    javaCodeFilename = filename;
  }

  public int describeContents() {
    return 0;
  }

  public void writeToParcel(Parcel out, int flags) {
    out.writeString(explanationFilename);
    out.writeString(pythonCodeFilename);
    out.writeString(cppCodeFilename);
    out.writeString(javaCodeFilename);
  }

  public static final Parcelable.Creator<AlgorithmAssets> CREATOR =
      new Parcelable.Creator<AlgorithmAssets>() {
        public AlgorithmAssets createFromParcel(Parcel in) {
          return new AlgorithmAssets(in);
        }

        public AlgorithmAssets[] newArray(int size) {
          return new AlgorithmAssets[size];
        }
      };

  private AlgorithmAssets(Parcel in) {
    explanationFilename = in.readString();
    pythonCodeFilename = in.readString();
    cppCodeFilename = in.readString();
    javaCodeFilename = in.readString();
  }
}
