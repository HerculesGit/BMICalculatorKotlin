package com.herco.bmicalculatorkotlin.shared.components

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.herco.bmicalculatorkotlin.R
import com.herco.bmicalculatorkotlin.shared.utils.ViewUtils

class CardButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private var tvGender: TextView
    private var imgGender: ImageView
    private var isChecked: Boolean = false

    init {
        inflate(context, R.layout.card_button_component, this)

        tvGender = findViewById(R.id.tv_gender)
        imgGender = findViewById(R.id.img_gender)

        ViewUtils.removeWhiteCorners(context, this)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CardButton,
            0, 0
        ).apply {
            try {
                val genderTextId =
                    getResourceId(R.styleable.CardButton_gender, R.string.male_gender)
                tvGender.setText(genderTextId)

                val icGenderResource =
                    getResourceId(R.styleable.CardButton_iconGender, R.drawable.ic_male_gender_24)
                imgGender.setImageResource(icGenderResource)


                val isChecked = getBoolean(R.styleable.CardButton_isChecked, false)
                setChecked(isChecked)
            } finally {
                recycle()
            }
        }
    }

    private fun rebuild() {
        invalidate()
        requestLayout()
    }

    fun setChecked(value: Boolean) {
        this.isChecked = value
        val bgCardColor = if (isChecked) R.color.selected_card else R.color.unselected_card
        val textColor = if (isChecked) R.color.white else R.color.text_gray
        val carView = findViewById<CardView>(R.id.card_view)
        carView.setCardBackgroundColor(ContextCompat.getColor(context, bgCardColor))
        tvGender.setTextColor(ContextCompat.getColor(context, textColor))

        rebuild()
    }
}