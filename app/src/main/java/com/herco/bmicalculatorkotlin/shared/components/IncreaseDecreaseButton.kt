package com.herco.bmicalculatorkotlin.shared.components

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.herco.bmicalculatorkotlin.R
import com.herco.bmicalculatorkotlin.shared.utils.ViewUtils

enum class ButtonType { INCREASE, DECREASE }

typealias OnIncreaseDecreaseListener = (value: Int) -> Unit

class IncreaseDecreaseButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {


    private var tvValue: TextView
    private var tvLabel: TextView
    var value: Int = 0

    private var increaseButton: ImageButton
    private var decreaseButton: ImageButton

    private lateinit var listener: OnIncreaseDecreaseListener

    init {
        inflate(context, R.layout.increase_decrease_button_component, this)
        tvLabel = findViewById(R.id.tv_label)
        tvValue = findViewById(R.id.tv_value)

        increaseButton = findViewById(R.id.img_increase)
        decreaseButton = findViewById(R.id.img_decrease)

        ViewUtils.removeWhiteCorners(context, this)
        context.theme.obtainStyledAttributes(
            attrs, R.styleable.IncreaseDecreaseButton, 0, 0
        ).apply {
            try {
                val labelId = getResourceId(
                    R.styleable.IncreaseDecreaseButton_buttonLabel, R.string.weight_label
                )
                tvLabel.setText(labelId)

                val valueId = getResourceId(
                    R.styleable.IncreaseDecreaseButton_increaseDefaultValue, R.string.weight_value
                )
                tvValue.setText(valueId)

                value = (tvValue.text as String).toInt()

            } finally {
                recycle()
            }
        }

        increaseButton.setOnClickListener {
            notifyDataChange(ButtonType.INCREASE)
        }

        decreaseButton.setOnClickListener {
            notifyDataChange(ButtonType.DECREASE)
        }
    }

    fun setListener(listener: OnIncreaseDecreaseListener) {
        this.listener = listener
    }

    private fun notifyDataChange(type: ButtonType) {
        if (type == ButtonType.INCREASE) value++ else value--

        listener.invoke(value)
        tvValue.text = value.toString()

        rebuild()
    }

    private fun rebuild() {
        invalidate()
        requestLayout()
    }
}
