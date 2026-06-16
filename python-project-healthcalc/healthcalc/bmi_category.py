from enum import Enum


class BMICategory(Enum):
    SEVERE_THINNESS = "Severe thinness"
    MODERATE_THINNESS = "Moderate thinness"
    MILD_THINNESS = "Mild thinness"
    NORMAL = "Normal weight"
    OVERWEIGHT = "Overweight"
    OBESE_CLASS_I = "Obese class I"
    OBESE_CLASS_II = "Obese class II"
    OBESE_CLASS_III = "Obese class III"