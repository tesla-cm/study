package cm.study.rxjava;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chenming on 2017/2/22.
 */
public class RxjavaFirst {

    private static final Logger ILOG = LoggerFactory.getLogger( RxjavaFirst.class );

    public static void main(String[] args) {
        //创建一个上游 Observable：
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        //创建一个下游 Observer
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                ILOG.debug("subscribe");
            }

            @Override
            public void onNext(Integer value) {
                ILOG.debug("" + value);
            }

            @Override
            public void onError(Throwable e) {
                ILOG.debug("error");
            }

            @Override
            public void onComplete() {
                ILOG.debug("complete");
            }
        };
        //建立连接
        observable.subscribe(observer);
    }
}
