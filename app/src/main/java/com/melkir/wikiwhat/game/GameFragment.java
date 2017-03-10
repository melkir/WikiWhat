package com.melkir.wikiwhat.game;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.melkir.wikiwhat.R;
import com.melkir.wikiwhat.data.model.Page;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class GameFragment extends Fragment implements GameContract.View {
    private static final String TAG = GameFragment.class.getSimpleName();

    private GameContract.Presenter mPresenter;

    @BindView(R.id.webView)
    WebView mWebView;

    @BindView(R.id.noPage)
    TextView mNoPageView;

    private List<String> mCachedParagraphs;
    private String mDisplayedParagraphs = "";
    private Page mPage;
    private TextView mClueCountView;
    private int mClueRemaining;
    private int mClueIndex;
    private ImageButton mClueButton;
    private Button mAnswerButton;
    private TextView mAnswerText;

    public static GameFragment newInstance() {
        return new GameFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCachedParagraphs = new ArrayList<>();
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
        ButterKnife.bind(this, root);

        mClueCountView = (TextView) getActivity().findViewById(R.id.clueCount);
        mClueButton = (ImageButton) getActivity().findViewById(R.id.btnUseClue);
        mAnswerButton = (Button) getActivity().findViewById(R.id.btnSubmitAnswer);
        mAnswerText = (TextView) getActivity().findViewById(R.id.editTextAnswer);

        mClueButton.setOnClickListener(l -> showNextClue());
        mAnswerButton.setOnClickListener(l -> submitAnswer());

        return root;
    }

    private void submitAnswer() {
        String answer = mAnswerText.getText().toString().toLowerCase();
        if (answer.equals(mPage.getTitle().toLowerCase())) displayToast("Correct");
        else displayToast("Incorrect the answser is " + mPage.getTitle());
    }

    private void showNextClue() {
        mClueRemaining--;
        if (mClueRemaining <= 0) {
            mClueButton.setOnClickListener(null);
            mClueButton.setEnabled(false);
            mClueButton.setAlpha(.5f);
        }
        mDisplayedParagraphs += mCachedParagraphs.get(mClueIndex++);
        mWebView.loadData(mDisplayedParagraphs, "text/html", "utf8");
        String clueRemainingTextView = Integer.toString(mClueRemaining);
        mClueCountView.setText(clueRemainingTextView);
    }

    @Override
    public void showPageContent(Page page) {
        String pageContent = page.getExtract();
        pageContent = pageContent.replaceAll("(?i)" + page.getTitle(), "XXX");
        mPage = page;

        parseHtmlParagraph(pageContent);
        initClueCounter(mCachedParagraphs.size());

        showNextClue();

        mWebView.setVisibility(View.VISIBLE);
        mNoPageView.setVisibility(View.GONE);
    }

    @Override
    public void showNoPageContent() {
        mWebView.setVisibility(View.GONE);
        mNoPageView.setVisibility(View.VISIBLE);
    }

    private void parseHtmlParagraph(String html) {
        Document doc = Jsoup.parse(html);
        Elements paragraphs = doc.select("p, h1, h2, ul, ol, dl");
        for (Element p : paragraphs) {
            if (p.text().length() > 0) mCachedParagraphs.add(p.toString());
        }
    }

    private void initClueCounter(int count) {
        if (count == 0) {
            displayToast("Unable to retrieve content for " + mPage.getPageid());
            Log.d(TAG, "id: " + mPage.getPageid() + " title: " + mPage.getTitle());
            mClueRemaining = 0;
        } else if (count >= 20) {
            // set max clues to 20
            mClueRemaining = 20;
        } else {
            // set max clues to the number of paragraphs
            mClueRemaining = count;
        }
    }

    @Override
    public void displayToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
