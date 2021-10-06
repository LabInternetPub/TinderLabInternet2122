package cat.tecnocampus.tinder2122.application.dto;

import java.time.LocalDate;

public class LikeDTO {
    private ProfileDTO target;
    private boolean matched;
    private LocalDate creationDate;
    private LocalDate matchDate;

    public LikeDTO() {
    }

    public LikeDTO(ProfileDTO target) {
        this.target = target;
        creationDate = LocalDate.now();
        matched = false;
        matchDate = null;
    }

    public ProfileDTO getTarget() {
        return target;
    }

    public void setTarget(ProfileDTO target) {
        this.target = target;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }
}
