package com.herco.bmicalculatorkotlin.home

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import com.herco.bmicalculatorkotlin.R
import com.herco.bmicalculatorkotlin.dto.BmiResultModel
import com.herco.bmicalculatorkotlin.result.YourResult
import com.herco.bmicalculatorkotlin.shared.utils.ViewUtils
import com.herco.bmicalculatorkotlin.shared.components.CardButton
import com.herco.bmicalculatorkotlin.shared.components.IncreaseDecreaseButton
import com.herco.bmicalculatorkotlin.shared.components.PrimaryButton
import com.herco.bmicalculatorkotlin.shared.constants.Constants
import com.herco.bmicalculatorkotlin.shared.enums.Gender


class HomeActivity : AppCompatActivity(), HomeViewContract.IHomeView {
    private lateinit var sliderHeight: Slider
    private lateinit var tvWeight: TextView

    private lateinit var cardMale: CardButton
    private lateinit var cardFemale: CardButton

    private lateinit var idWeightButton: IncreaseDecreaseButton;
    private lateinit var idAgeButton: IncreaseDecreaseButton;

    private lateinit var btnCalculate: PrimaryButton

    private var weight: Float = 0f

    private lateinit var presenter: HomeViewContract.IHomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    private fun initUI() {
        cardMale = findViewById(R.id.card_male)
        cardFemale = findViewById(R.id.card_female)
        sliderHeight = findViewById(R.id.slider_height)
        tvWeight = findViewById(R.id.tv_weight)
        btnCalculate = findViewById(R.id.btn_calculate)
        idWeightButton = findViewById(R.id.increase_decrease_weight_button)
        idAgeButton = findViewById(R.id.increase_decrease_age_button)
        presenter = HomePresenter(this)

        ViewUtils.setAppBar(this)
        addActionListeners()
    }

    private fun addActionListeners() {
        sliderHeight.addOnChangeListener { _, value, _ ->
            weight = value

            val formattedValue = "${value.toInt()}"
            tvWeight.text = formattedValue
            presenter.onHeightChanged(formattedValue)
        }

        cardMale.setOnClickListener {
            presenter.onGenderClicked(Gender.MALE.gender)
            cardMale.setChecked(true)
            cardFemale.setChecked(false)
        }

        cardFemale.setOnClickListener {
            presenter.onGenderClicked(Gender.FEMALE.gender)
            cardFemale.setChecked(true)
            cardMale.setChecked(false)
        }

        btnCalculate.setOnClickListener { presenter.calculateBmi() }

        idWeightButton.setListener { weight -> presenter.onWeightChanged(weight) }

        idAgeButton.setListener { age -> presenter.onAgeChanged(age) }
    }

    private fun goToYourResultScreen(resultModel: BmiResultModel) {
        val intent = Intent(this, YourResult::class.java)
        intent.putExtra(Constants.BMI_MODEL_KEY, resultModel.toStringJson())
        startActivity(intent)
    }

    override fun calculateSuccess(resultModel: BmiResultModel) {
        goToYourResultScreen(resultModel)
    }
}