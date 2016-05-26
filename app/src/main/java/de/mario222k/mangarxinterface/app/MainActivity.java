package de.mario222k.mangarxinterface.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import de.mario222k.mangarxinterface.model.Chapter;
import de.mario222k.mangarxinterface.model.Manga;
import de.mario222k.mangarxinterface.model.Page;

public class MainActivity extends AppCompatActivity {

    private TextView mTestText;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTestText = (TextView) findViewById(R.id.testText);

        testMangaAsBundle();
    }

    @SuppressLint("SetTextI18n")
    private void testMangaAsBundle() {
        mTestText.setText("test is running...");

        try {
            Manga manga = new Manga("testManga");
            manga.setUrl("http://testurl.de");
            manga.setCover("http://testurl.de/cover.jpg");

            Chapter chapter1 = new Chapter("testChapter1", "http://testurl.de/1");
            chapter1.addPage(0, new Page(0, "http://testurl.de/1/0"));
            manga.getChapters().add(chapter1);

            Chapter chapter2 = new Chapter("testChapter2", "http://testurl.de/2");
            chapter2.addPage(1, new Page(1, "http://testurl.de/2/1"));
            chapter2.addPage(0, new Page(0, "http://testurl.de/2/0"));
            chapter2.addPage(2, new Page(1, "http://testurl.de/2/2"));
            manga.getChapters().add(chapter2);

            Parcel parcel = Parcel.obtain();
            manga.writeToParcel(parcel, 0);
            parcel.setDataPosition(0);
            Manga parcelManga = Manga.CREATOR.createFromParcel(parcel);

            mTestText.setText(manga + " == " + parcelManga + " : " + manga.equals(parcelManga));

        } catch (Exception e) {
            e.printStackTrace();
            mTestText.setText("test failed: " + e.getMessage());
        }
    }
}
