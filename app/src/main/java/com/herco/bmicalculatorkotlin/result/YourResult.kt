package com.herco.bmicalculatorkotlin.result

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.herco.bmicalculatorkotlin.R
import com.herco.bmicalculatorkotlin.dto.BmiResultModel
import com.herco.bmicalculatorkotlin.shared.components.PrimaryButton
import com.herco.bmicalculatorkotlin.shared.constants.Constants
import com.herco.bmicalculatorkotlin.shared.utils.ViewUtils

class YourResult : AppCompatActivity(), ResultViewContract.IResultView {
    private lateinit var tvStatus: TextView
    private lateinit var tvResult: TextView
    private lateinit var tvMessage: TextView
    private lateinit var btnRecalculate: PrimaryButton

    private lateinit var presenter: ResultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_result)

        this.presenter = ResultPresenter(this)
        initUI()
    }

    private fun initUI() {
        tvStatus = findViewById(R.id.tv_bmi_status_label)
        tvMessage = findViewById(R.id.tv_bmi_message)
        tvResult = findViewById(R.id.tv_bmi_result)
        btnRecalculate = findViewById(R.id.btn_recalculate)

        ViewUtils.setAppBar(this)
        presenter.loadData(intent.getStringExtra(Constants.BMI_MODEL_KEY)!!)
        addListeners()
    }

    private fun addListeners() {
        btnRecalculate.setOnClickListener { finish() }
    }

    override fun setBmi(bmiModel: BmiResultModel) {
        val (status, message, result) = bmiModel

        tvStatus.setText(status)
        tvMessage.setText(message)
        tvResult.text = String.format("%.1f", result)
    }
}