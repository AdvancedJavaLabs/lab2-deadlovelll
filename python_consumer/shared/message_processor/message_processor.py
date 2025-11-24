from collections import Counter
import re
from typing import Union

from shared.profane_detector import ProfaneDetecor
from shared.name_detector import NameDetector


class MessageProcessor:
    
    WORD_RE = re.compile(r"[a-zA-Z]+")
        
    __slots__ = (
        '_profane_detector',
        '_name_detector',
    )
    
    def __init__(
        self,
        profane_detector: ProfaneDetecor = ProfaneDetecor(),
        name_detector: NameDetector = NameDetector(),
    ) -> None:
        
        self._profane_detector = profane_detector
        self._name_detector = name_detector
    
    def process(
        self, 
        text: list[str],
    ) -> dict[str, Union[int, dict[str, int]]]:
        
        counter = Counter()
        word_count = 0
        bad_words = 0
        names = 0

        for word in text:
            for subword in word.split('_'):
                if not subword:
                    continue
                w = subword.lower()

                match = self.WORD_RE.fullmatch(w)
                if not match:
                    continue
                w = match.group()

                word_count += 1
                counter[w] += 1

                if self._profane_detector.is_profane(w):
                    bad_words += 1

                if self._name_detector.is_name(w):
                    names += 1

        top_5 = counter.most_common(5)

        return {
            "word_count": word_count,
            "bad_words": bad_words,
            "names": names,
            "top_5": top_5
        }