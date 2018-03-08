package de.mario222k.mangarxinterface.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class Manga(
        val name: String,
        val url: String? = null,
        val cover: String? = null,
        val chapters: List<Chapter>? = null) : Parcelable {

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(url)
        dest.writeString(cover)
        dest.writeTypedList(chapters)
    }

    companion object {
        @JvmField // requested by android to keep static field
        val CREATOR: Parcelable.Creator<Manga> = object : Parcelable.Creator<Manga> {
            override fun createFromParcel(`in`: Parcel) = Manga(
                    `in`.readString(),
                    `in`.readString(),
                    `in`.readString(),
                    ArrayList<Chapter>().apply {
                        `in`.readTypedList(this, Chapter.CREATOR)
                    }
            )

            override fun newArray(size: Int) = Array(size, { _ -> Manga("") })
        }
    }
}
