package com.melkir.wikiwhat.game;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melkir.wikiwhat.R;

import static com.google.common.base.Preconditions.checkNotNull;

public class GameFragment extends Fragment implements GameContract.View {
    private GameContract.Presenter mPresenter;

    public static GameFragment newInstance() {
        return new GameFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.stop();
    }

    @Override
    public void setPresenter(@NonNull GameContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.game_fragment, container, false);
        return root;
    }
}
