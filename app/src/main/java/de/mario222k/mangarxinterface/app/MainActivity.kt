package de.mario222k.mangarxinterface.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcel
import android.support.v7.app.AppCompatActivity
import de.mario222k.mangarxinterface.model.Chapter
import de.mario222k.mangarxinterface.model.Manga
import de.mario222k.mangarxinterface.model.Page
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testMangaAsBundle()
    }

    @SuppressLint("SetTextI18n")
    private fun testMangaAsBundle() {
        testText?.text = "test is running..."

        try {

            val chapters = listOf(
                    Chapter("testChapter1", "http://testurl.de/1", listOf(Page(0, "http://testurl.de/1/0"))),
                    Chapter("testChapter2", "http://testurl.de/2", listOf(
                            Page(1, "http://testurl.de/2/1"),
                            Page(0, "http://testurl.de/2/0"),
                            Page(2, "http://testurl.de/2/2")))
            )

            val manga = Manga("testManga", "http://testurl.de", "http://testurl.de/cover.jpg", chapters)

            val parcel = Parcel.obtain()
            manga.writeToParcel(parcel, 0)
            parcel.setDataPosition(0)
            val parcelManga = Manga.CREATOR.createFromParcel(parcel) as Manga

            testText?.text = "works: ${manga == parcelManga}"

        } catch (e: Exception) {
            e.printStackTrace()
            testText?.text = "test failed: " + e.message
        }

    }
}
