package de.mario222k.mangarxinterface.model

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class Chapter(
        val name: String? = null,
        val url: String? = null,
        val pages: List<Page>? = null) : Parcelable {

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(url)
        dest.writeTypedList(pages)
    }

    companion object {
        @JvmField // requested by android to keep static field
        val CREATOR: Parcelable.Creator<Chapter> = object : Parcelable.Creator<Chapter> {
            override fun createFromParcel(`in`: Parcel) = Chapter(
                    `in`.readString(),
                    `in`.readString(),
                    ArrayList<Page>().apply {
                        `in`.readTypedList(this, Page.CREATOR)
                    }
            )

            override fun newArray(size: Int) = Array(size, { _ -> Chapter("", "") })
        }
    }
}
