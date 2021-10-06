package cat.tecnocampus.tinder2122.application;

import cat.tecnocampus.tinder2122.application.dto.ProfileDTO;
import cat.tecnocampus.tinder2122.domain.Like;

import java.util.List;

public interface ProfileDAO {

    ProfileDTO getProfileLazy(String id);
    List<ProfileDTO> getProfilesLazy();

    ProfileDTO getProfile(String id);
    List<ProfileDTO> getProfiles();

    ProfileDTO addProfile(ProfileDTO profile);

    void saveLikes(String origin, List<Like> likes);

    void updateLikeToMatch(String id, String id1);
}
