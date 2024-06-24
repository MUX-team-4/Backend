package com.th.mux.mapper;

import com.th.mux.dto.ProfileDto;
import com.th.mux.model.Profile;

public class ProfileMapper {
    public static ProfileDto toDto(Profile profile) {
        if (profile == null) {
            return null;
        }
        return new ProfileDto(profile.getDailyGoal(), profile.getHeight(), profile.getStepLength());
    }
}
