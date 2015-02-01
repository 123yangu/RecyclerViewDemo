package com.pruebas.lgvalle.mispruebas;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.DisplayMetrics;
import android.util.SparseIntArray;

/**
 * Created by lgvalle on 31/01/15.
 */
public class AutoSpanGridLayoutManager extends GridLayoutManager {
    private SparseIntArray sparseIntArray;
    private float columnWidth;


    public AutoSpanGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
        init(context, spanCount);
    }

    public AutoSpanGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
        init(context, spanCount);
    }


    private void init(Context context, int spanCount) {
        sparseIntArray = new SparseIntArray();
        columnWidth = calculateColumnWidth(context, spanCount);
        setSpanSizeLookup(new SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return sparseIntArray.get(position);
            }
        });
    }

    private float calculateColumnWidth(Context context, int spanCount) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels / spanCount;
    }

    public void calculateItemSpan(int position, float viewWidth) {
        double calculatedSpan = Math.ceil(viewWidth / columnWidth);
        int spanSize = (int) Math.min(getSpanCount(), Math.max(1, calculatedSpan));
        saveItemSpan(position, spanSize);
    }

    private void saveItemSpan(int position, int spanSize) {
        sparseIntArray.put(position, spanSize);
    }
}