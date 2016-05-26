package de.mario222k.mangarxinterface.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Manga : Parcelable {
    var name: String
    var url: String? = null
    var cover: String? = null
    val chapters: ArrayList<Chapter>

    constructor(name: String) {
        this.name = name
        chapters = ArrayList<Chapter>()
    }

    private constructor(`in`: Parcel) {
        name = `in`.readString()
        url = `in`.readString()
        cover = `in`.readString()
        chapters = ArrayList()
        `in`.readTypedList(chapters, Chapter.CREATOR)
    }

    override fun equals(other: Any?): Boolean {
        if(other !is Manga) {
            return false
        }

        if(chapters.size != other.chapters.size) {
            return false
        }

        for(chapter in chapters) {
            if (!other.chapters.contains(chapter)) {
                return false
            }
        }

        return name.equals(other.name) &&
                url.equals(other.url) &&
                cover.equals(other.cover)
    }

    override fun hashCode(): Int {
        var hash = name.hashCode()
        hash = hash * 31 + (url?.hashCode() ?: 0)
        hash = hash * 31 + (cover?.hashCode() ?: 0)

        for(chapter in chapters) {
            hash = hash * 31 + chapter.hashCode()
        }

        return hash
    }

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
            override fun createFromParcel(`in`: Parcel) = Manga(`in`)
            override fun newArray(size: Int) = Array(size, { i -> Manga("") })
        }
    }
}
