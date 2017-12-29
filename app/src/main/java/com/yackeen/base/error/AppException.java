package com.yackeen.base.error;

import android.support.annotation.IntDef;

import com.yackeen.utils.TextUtils;
import com.yackeen.yackeen.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;


public class AppException extends Exception {

    public static final int NETWORK_ERROR = 1;
    public static final int NO_DATA_ERROR = 2;
    public static final int UNKNOWN_ERROR = 3;
    private int errorCode;
    private String errorMessage;

    public AppException(@ErrorCode int errorCode, String errorMessage) {
        this(errorCode, errorMessage, null);
    }

    public AppException(@ErrorCode int errorCode, String errorMessage, Throwable original) {
        super(original);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public static Throwable adapt(Throwable t) {
        if (t instanceof UnknownHostException || t instanceof SocketException || t instanceof SocketTimeoutException) {
            return new AppException(NETWORK_ERROR, TextUtils.getString(R.string.no_internet_connection), t);
        } else {
            return new AppException(UNKNOWN_ERROR, TextUtils.getString(R.string.something_went_wrong), t);
        }
    }

    public @ErrorCode
    int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @IntDef({NETWORK_ERROR, NO_DATA_ERROR, UNKNOWN_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorCode {
    }

}
