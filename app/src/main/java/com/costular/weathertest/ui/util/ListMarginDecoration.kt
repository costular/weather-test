package com.costular.weathertest.ui.util

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ListMarginDecoration(resources: Resources,
                           marginDps: Int = 16) : RecyclerView.ItemDecoration() {

    var marginPx: Int = marginDps.toPx(resources)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent != null && view != null) {
            val itemPosition = parent.getChildAdapterPosition(view)
            val totalCount = parent.adapter?.itemCount ?: 0

            if (itemPosition >= 0 && itemPosition <= totalCount - 1) {
                outRect.left = marginPx
                outRect.right = marginPx

                if (itemPosition < totalCount - 1) {
                    outRect.bottom = marginPx
                }
            }
        }
    }

}