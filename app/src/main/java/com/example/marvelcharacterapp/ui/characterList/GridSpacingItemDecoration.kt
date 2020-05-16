package com.example.marvelcharacterapp.ui.characterList

import android.content.res.Resources
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.marvelcharacterapp.MarvelApplication
import kotlin.math.roundToInt


class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdge: Boolean
) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % spanCount // item column
        if (includeEdge) {
            outRect.left =
                dpToPx(spacing) - column * dpToPx(spacing) / spanCount // spacing - column * ((1f / spanCount) * spacing)
            outRect.right =
                (column + 1) * dpToPx(spacing) / spanCount // (column + 1) * ((1f / spanCount) * spacing)
            if (position < spanCount) { // top edge
                outRect.top = dpToPx(spacing)
            }
            outRect.bottom = dpToPx(spacing) // item bottom
        } else {
            outRect.left = column * dpToPx(spacing) / spanCount // column * ((1f / spanCount) * spacing)
            outRect.right =
                dpToPx(spacing) - (column + 1) * dpToPx(spacing) / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = dpToPx(spacing) // item top
            }
        }
    }

    private fun dpToPx(dp: Int): Int {
        val r: Resources? = MarvelApplication.instance?.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            r?.displayMetrics
        ).roundToInt()
    }

}