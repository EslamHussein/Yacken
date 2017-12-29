package com.yackeen.base.error;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


public class ErrorManager {

    private ErrorManager() { /* No instances */ }

    public static <T> Observable<T> wrap(Observable<T> observable) {
        return observable.onErrorResumeNext(new ExceptionsInterceptor<T>());
    }

    /**
     * Maps java exceptions to the approriate {@link AppException}
     */
    private static class ExceptionsInterceptor<T> implements Function<Throwable, Observable<T>> {


        @Override
        public Observable<T> apply(Throwable throwable) throws Exception {
            return Observable.error(AppException.adapt(throwable));
        }
    }

}
