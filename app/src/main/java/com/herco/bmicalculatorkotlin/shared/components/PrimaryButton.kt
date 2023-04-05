package com.herco.bmicalculatorkotlin.shared.components

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.herco.bmicalculatorkotlin.R

class PrimaryButton(context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {

    private var tvLabel: TextView

    init {
        inflate(context, R.layout.primary_button_component, this)

        tvLabel = findViewById(R.id.tv_label)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PrimaryButton,
            0, 0
        ).apply {
            try {
                val labelId = getResourceId(R.styleable.PrimaryButton_label, R.string.calculate)
                tvLabel.setText(labelId)
            } finally {
                recycle()
            }
        }
    }

    private fun rebuild() {
        invalidate()
        requestLayout()
    }

    fun setLabel(value: String) {
        tvLabel.text = value
        rebuild()
    }
}