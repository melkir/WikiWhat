package com.melkir.wikiwhat.game;

import com.melkir.wikiwhat.BasePresenter;
import com.melkir.wikiwhat.BaseView;

public interface GameContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void stop();
    }
}
