package com.comiccoder.a7minutesworkout.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.comiccoder.a7minutesworkout.R
import com.comiccoder.a7minutesworkout.databinding.ActivityBmiactivityBinding
import com.google.android.material.snackbar.Snackbar
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object {
        private const val METRIC_UNITS_VIEW = "METRIC_UNITS_VIEW" // Metric units view
        private const val US_UNITS_VIEW = "US_UNITS_VIEW" // US units view
    }

    private var currentVisibleView = METRIC_UNITS_VIEW

    private var binding: ActivityBmiactivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmiActivity)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"
        }
        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        makeVisibleMetricUnitsView()
        binding?.rgUnits?.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.rbMetricUnits) {
                makeVisibleMetricUnitsView()
            } else {
                makeVisibleUSUnitsView()
            }
        }

        binding?.btnCalculateBMI?.setOnClickListener {
            calculateUnits()
        }
    }

    private fun makeVisibleMetricUnitsView() {
        currentVisibleView = METRIC_UNITS_VIEW
        binding?.tilWeight?.visibility = View.VISIBLE
        binding?.tilHeight?.visibility = View.VISIBLE

        binding?.tilWeightUs?.visibility = View.INVISIBLE
        binding?.llHeightUs?.visibility = View.INVISIBLE

        binding?.edtWeight?.text?.clear() // height value is cleared before reappearing
        binding?.edtHeight?.text?.clear() // w eight value is cleared before reappearing

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun makeVisibleUSUnitsView() {
        currentVisibleView = US_UNITS_VIEW
        binding?.tilWeightUs?.visibility = View.VISIBLE
        binding?.llHeightUs?.visibility = View.VISIBLE

        binding?.tilWeight?.visibility = View.INVISIBLE
        binding?.tilHeight?.visibility = View.INVISIBLE

        binding?.edtWeightUs?.text?.clear() // height value is cleared before reappearing
        binding?.edtHeightFeetUs?.text?.clear() // w eight value is cleared before reappearing
        binding?.edtHeightInchUs?.text?.clear() // w eight value is cleared before reappearing

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun displayBMIResult(bmi: Float) {
        val bmiLabel: String
        val bmiDescription: String

        if (bmi <= 15) {
            bmiLabel = "Very Severely Underweight"
            bmiDescription = "Have some nutritious food, gain some weight, Take Care! "
        } else if (bmi > 15 && bmi <= 16) {
            bmiLabel = "Severely Underweight"
            bmiDescription = "Have some nutritious food, gain some weight, Take Care! "
        } else if (bmi > 16 && bmi <= 18.5) {
            bmiLabel = "Underweight"
            bmiDescription = "Have some nutritious food, gain some weight, Take Care! "
        } else if (bmi > 18.5 && bmi <= 25) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (bmi > 25 && bmi <= 30) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of yourself! Workout"
        } else if (bmi > 30 && bmi <= 35) {
            bmiLabel = "Obese Class | (Moderately Obese)"
            bmiDescription = "You are in a very dangerous condition! Act now"
        } else if (bmi > 35 && bmi <= 40) {
            bmiLabel = "Obese Class || (Severely Obese)"
            bmiDescription = "You are in a very dangerous condition! Act now"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely Obese)"
            bmiDescription = "You are in a very dangerous condition! Act now"
        }

        val bmiValue = BigDecimal(bmi.toDouble())
            .setScale(2, RoundingMode.HALF_EVEN).toString()

        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
        binding?.tvBMIValue?.text = bmiValue
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDescription


    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true

        if (binding?.edtWeight?.text.toString().isEmpty()) {
            isValid = false
        } else if (binding?.edtHeight?.text.toString().isEmpty()) {
            isValid = false
        }
        return isValid
    }

    private fun validateUsUnits(): Boolean {
        var isValid = true

        if (binding?.edtWeightUs?.text.toString().isEmpty()) {
            isValid = false
        } else if (binding?.edtHeightFeetUs?.text.toString().isEmpty()) {
            isValid = false
        } else if (binding?.edtHeightInchUs?.text.toString().isEmpty()) {
            isValid = false
        }
        return isValid
    }

    private fun calculateUnits() {
        if (currentVisibleView == METRIC_UNITS_VIEW) {
            if (validateMetricUnits()) {
                val height = binding?.edtHeight?.text.toString().toFloat() / 100
                val weight = binding?.edtWeight?.text.toString().toFloat()

                val bmi = weight / (height * height)
                displayBMIResult(bmi)
            } else {
                Snackbar.make(binding!!.root, "Please Enter Valid Values", Snackbar.LENGTH_SHORT)
                    .show()
            }
        } else {
            if (validateUsUnits()) {
                val usUnitHeightValueFeet = binding?.edtHeightFeetUs?.text?.toString()?.toFloat()
                val usUnitHeightValueInch = binding?.edtHeightInchUs?.text?.toString()?.toFloat()
                val usUnitWeightValue = binding?.edtWeightUs?.text?.toString()?.toFloat()

                val heightValue = usUnitHeightValueInch!! + (usUnitHeightValueFeet!! * 12)

                val bmi = 703 * (usUnitWeightValue!! / (heightValue * heightValue))
                displayBMIResult(bmi)
            } else {
                Snackbar.make(binding!!.root, "Please Enter Valid Values", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }
}