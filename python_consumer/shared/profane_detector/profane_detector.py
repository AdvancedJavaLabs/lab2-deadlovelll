from better_profanity import profanity


class ProfaneDetecor:
    
    def is_profane(self, word: str) -> bool:
        if len(word) < 3:
            return False
        return profanity.contains_profanity(word)