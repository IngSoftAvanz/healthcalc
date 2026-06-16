from abc import ABC, abstractmethod

from healthcalc.person import Person


class IdealBodyWeight(ABC):
    """Interface for ideal body weight metrics."""

    @abstractmethod
    def ibw_from_person(self, person: Person) -> float:
        pass