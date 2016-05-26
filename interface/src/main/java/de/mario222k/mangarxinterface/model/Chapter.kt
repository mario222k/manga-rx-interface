package de.mario222k.mangarxinterface.model

import android.os.Parcel
import android.os.Parcelable
import android.util.SparseArray

class Chapter : Parcelable {
    var name: String? = null
        private set
    var url: String? = null
        private set
    var pages: SparseArray<Page>? = null
        private set

    constructor(name: String, url: String) {
        this.name = name
        this.url = url
        pages = null
    }

    private constructor(`in`: Parcel) {
        name = `in`.readString()
        url = `in`.readString()
        setPagesFromArray(`in`.createTypedArray(Page.CREATOR))
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(url)
        dest.writeTypedArray(getArrayFromPages(), flags)
    }

    override fun equals(other: Any?): Boolean {
        if(other !is Chapter) {
            return false
        }

        if(getPageCount() != other.getPageCount()) {
            return false
        }

        if(pages == null && other.pages != null) {
            return false
        }

        if(pages != null && other.pages == null) {
            return false
        }

        val arrayOfPages = getArrayFromPages()
        if(arrayOfPages != null) {
            for (page in arrayOfPages) {
                if (!other.pageContains(page)) {
                    return false
                }
            }
        }

        return name.equals(other.name) &&
                url.equals(other.url)
    }

    override fun hashCode(): Int {
        var hash = name?.hashCode() ?: 0
        hash = hash * 31 + (url?.hashCode() ?: 0)

        val arrayOfPages = getArrayFromPages() ?: return hash
        for(page in arrayOfPages) {
            hash = hash * 31 + page.hashCode()
        }

        return hash
    }

    fun addPage(index: Int, page: Page) {
        if(pages == null) {
            pages = SparseArray(index)
        }

        pages?.put(index, page)
    }

    /**
     * Hold information about loaded pages. Should be equal with
     * Pages size or `-1` for not loaded.

     * @return chapter page count or `-1`
     */
    fun getPageCount() = pages?.size() ?: -1

    private fun pageContains(page: Page?): Boolean {
        if (page == null) {
            return false
        }
        for (i in 0..getPageCount() - 1) {
            if (page.equals(pages?.get(i))) {
                return true
            }
        }
        return false
    }

    private fun getArrayFromPages(): Array<Page>? {
        if (pages == null) {
            return null
        }

        val length = pages?.size() ?: 0
        return Array(length, { i -> pages?.get(i) ?: Page(i, "") })
    }

    private fun setPagesFromArray(array: Array<Page>?) {
        if (array == null) {
            pages = null
            return
        }

        val count = array.size
        if(pages == null) {
            pages = SparseArray(count)
        }
        for (i in 0..count - 1) {
            addPage(i, array[i])
        }
    }

    companion object {
        @JvmField // requested by android to keep static field
        val CREATOR: Parcelable.Creator<Chapter> = object : Parcelable.Creator<Chapter> {
            override fun createFromParcel(`in`: Parcel) = Chapter(`in`)
            override fun newArray(size: Int) = Array(size, { i -> Chapter("", "") })
        }
    }
}
