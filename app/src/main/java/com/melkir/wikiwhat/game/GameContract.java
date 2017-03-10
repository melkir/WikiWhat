package com.melkir.wikiwhat.game;

import com.melkir.wikiwhat.BasePresenter;
import com.melkir.wikiwhat.BaseView;
import com.melkir.wikiwhat.data.model.Page;

public interface GameContract {
    interface View extends BaseView<Presenter> {

        void showPageContent(Page page);

        void showNoPageContent();

        void displayToast(String message);
    }

    interface Presenter extends BasePresenter {
        void stop();
    }
}
