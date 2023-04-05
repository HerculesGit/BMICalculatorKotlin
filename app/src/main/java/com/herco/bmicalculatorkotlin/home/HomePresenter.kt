package com.herco.bmicalculatorkotlin.home

import com.herco.bmicalculatorkotlin.R
import com.herco.bmicalculatorkotlin.dto.BmiResultModel
import com.herco.bmicalculatorkotlin.shared.enums.Gender
import kotlin.math.pow

class HomePresenter(private val view: HomeViewContract.IHomeView) :
    HomeViewContract.IHomePresenter {
    var selectedGender: Int = Gender.MALE.gender
    var height: Int = 177
    var weight: Int = 70
    var age: Int = 20

    override fun onGenderClicked(gender: Int) {
        this.selectedGender = gender
    }

    override fun onHeightChanged(height: String) {
        this.height = height.toInt()
    }

    override fun onWeightChanged(weight: Int) {
        this.weight = weight
    }

    override fun onAgeChanged(age: Int) {
        this.age = age
    }

    override fun calculateBmi() {
        val bmiResult = BmiResultModel(
            status = R.string.underweight_status,
            message = R.string.underweight_msg,
            result = weight / (height.div(100f)).pow(2)
        )

        when (bmiResult.result) {
            in 0f..18.4f -> {
                bmiResult.message = R.string.underweight_msg
                bmiResult.status = R.string.underweight_status
            }
            in 18.5f..24.9f -> {
                bmiResult.message = R.string.normal_weight_msg
                bmiResult.status = R.string.normal_weight_status
            }
            in 25f..29.9f -> {
                bmiResult.message = R.string.overweight_msg
                bmiResult.status = R.string.overweight_status
            }
            else -> {
                bmiResult.message = R.string.obesity_msg
                bmiResult.status = R.string.obesity_status
            }
        }

        this.view.calculateSuccess(bmiResult)
    }
}