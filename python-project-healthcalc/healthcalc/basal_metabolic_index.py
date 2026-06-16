from abc import ABC, abstractmethod

from healthcalc.person import Person
from healthcalc.bmi_category import BMICategory


class BasalMetabolicIndex(ABC):
    """Interface for BMI-related metrics."""

    @abstractmethod
    def bmi_from_person(self, person: Person) -> float:
        pass

    @abstractmethod
    def bmi_category_from_person(self, person: Person) -> BMICategory:
        pass