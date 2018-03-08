package de.mario222k.mangarxinterface.model

import android.os.Parcel
import android.os.Parcelable

data class Page(
        val page: Int = 0,
        val url: String? = "") : Parcelable {

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(page)
        dest.writeString(url)
    }

    companion object {
        @JvmField // requested by android to keep static field
        val CREATOR: Parcelable.Creator<Page> = object : Parcelable.Creator<Page> {
            override fun createFromParcel(`in`: Parcel) = Page(`in`.readInt(), `in`.readString())
            override fun newArray(size: Int) = Array(size, { _ -> Page(-1, "") })
        }
    }
}
