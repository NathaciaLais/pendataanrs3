package Nathacia.uas.pendataanrs;

import com.google.gson.annotations.SerializedName;

public class ValueNulData {
    @SerializedName("success")
    private int success;

    @SerializedName("message")
    private String message;

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
