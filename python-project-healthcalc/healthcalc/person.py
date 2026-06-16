from abc import ABC, abstractmethod
from dataclasses import dataclass

from healthcalc.gender import Gender


class Person(ABC):
    """Interface that represents the data required to calculate health metrics."""

    @abstractmethod
    def weight(self) -> float:
        pass

    @abstractmethod
    def height(self) -> float:
        pass

    @abstractmethod
    def gender(self) -> Gender:
        pass

    @abstractmethod
    def age(self) -> int:
        pass


@dataclass
class BasicPerson(Person):
    """Simple implementation of Person."""

    person_weight: float
    person_height: float
    person_gender: Gender
    person_age: int

    def weight(self) -> float:
        return self.person_weight

    def height(self) -> float:
        return self.person_height

    def gender(self) -> Gender:
        return self.person_gender

    def age(self) -> int:
        return self.person_age