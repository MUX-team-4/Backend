package com.th.mux.service;

import com.th.mux.dto.ProfileDto;
import com.th.mux.mapper.ProfileMapper;
import com.th.mux.model.Profile;
import com.th.mux.model.User;
import com.th.mux.repository.ProfileRepository;
import com.th.mux.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
@Slf4j
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public ProfileDto updateProfile(long userId, ProfileDto profileDto) {
        log.info("updateProfile userId={}", userId);
        Optional<User> userOp = userRepository.findById(userId);
        Optional<Profile> profileOp = profileRepository.getProfileByUserId(userId);
        Profile currentProfile;
        if (profileOp.isPresent()) {
            log.info("updateProfile update");
            // update
            currentProfile = profileOp.get();
            if (currentProfile.getDailyGoal() != profileDto.getDailyGoal()) {
                currentProfile.setDailyGoal(profileDto.getDailyGoal());
            }
            if (currentProfile.getHeight() != profileDto.getHeight()) {
                currentProfile.setHeight(profileDto.getHeight());
            }
            if (currentProfile.getStepLength() != profileDto.getStepLength()) {
                currentProfile.setStepLength(profileDto.getStepLength());
            }
        } else {
            log.info("updateProfile insert");
            // insert
            currentProfile = new Profile();
            currentProfile.setUser(userOp.get());
            //log.info("userId insert id={}", userOp.get().getId());
            currentProfile.setDailyGoal(profileDto.getDailyGoal());
            currentProfile.setHeight(profileDto.getHeight());
            currentProfile.setStepLength(profileDto.getStepLength());
        }
        return ProfileMapper.toDto(profileRepository.save(currentProfile));
    }

    public ProfileDto getProfile(long userId) {
        return profileRepository.getProfileByUserId(userId).map(ProfileMapper::toDto).orElse(null);
    }

}
