package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.dto.ratings.*;
import com.ciphertext.opencarebackend.model.entity.*;
import com.ciphertext.opencarebackend.repository.*;
import com.ciphertext.opencarebackend.service.RatingOptionHandler;
import com.ciphertext.opencarebackend.service.RatingService;
import com.ciphertext.opencarebackend.service.UserDetailsParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingServiceImpl implements RatingService {
    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;
    private final RatingRepository ratingRepository;
    private final RatingOptionHandler ratingOptionHandler;
    private final UserDetailsParser userDetails;
    private final UserRepository userRepository;
    private final RatingOptionsRepository ratingOptionsRepository;

    @Override
    @SneakyThrows
    public void addOrUpdateRatingToDoctor(UserRatingViewerDTO userRating, int doctorId) {
        ObjectMapper objectMapper = new ObjectMapper();
        Doctor doctor = doctorRepository.findById((long) doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("no doctor found"));
        int currentUser = userDetails.getCurrentUseId();
        //are any user able to make multiple rating to a specific doctor ?
        Optional<Rating> existenceRating = ratingRepository.findByUser_IdAndDoctor_Id(currentUser, doctorId);
        Rating rating;
        if (existenceRating.isPresent()) {
            rating = existenceRating.get();
            rating.setDoctor(doctor);
            rating.setComments(userRating.getRating().getComments());
            rating.setUser(userDetails.getCurrentUser());
            List<RatingOptionDTO> updatedRatingOptions = ratingOptionHandler.addOrUpdateRating(rating.getRating_options(), userRating.getRating().getRatings());
            rating.setRating_options(objectMapper.writeValueAsString(updatedRatingOptions));
        } else {
            rating = new Rating();
            rating.setDoctor(doctor);
            rating.setComments(userRating.getRating().getComments());
            rating.setUser(userDetails.getCurrentUser());
            List<RatingOptionDTO> updatedRatingOptions = ratingOptionHandler.addOrUpdateRating(rating.getRating_options(), userRating.getRating().getRatings());
            rating.setRating_options(objectMapper.writeValueAsString(updatedRatingOptions));
        }
        ratingRepository.save(rating);
    }

    @Override
    @SneakyThrows
    public void addOrUpdateRatingToHospital(UserRatingViewerDTO userRating, int hospitalId) {
        ObjectMapper objectMapper = new ObjectMapper();
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("no doctor found"));
        int currentUser = userDetails.getCurrentUseId();
        //are any user able to make multiple rating to a specific hospital ?
        Optional<Rating> existenceRating = ratingRepository.findByUser_IdAndHospital_Id(currentUser, hospitalId);
        Rating rating;
        if (existenceRating.isPresent()) {
            rating = existenceRating.get();
            rating.setHospital(hospital);
            rating.setComments(userRating.getRating().getComments());
            rating.setUser(userDetails.getCurrentUser());
            List<RatingOptionDTO> updatedRatingOptions = ratingOptionHandler.addOrUpdateRating(rating.getRating_options(), userRating.getRating().getRatings());
            rating.setRating_options(objectMapper.writeValueAsString(updatedRatingOptions));
        } else {
            rating = new Rating();
            rating.setHospital(hospital);
            rating.setComments(userRating.getRating().getComments());
            rating.setUser(userDetails.getCurrentUser());
            List<RatingOptionDTO> updatedRatingOptions = ratingOptionHandler.addOrUpdateRating(rating.getRating_options(), userRating.getRating().getRatings());
            rating.setRating_options(objectMapper.writeValueAsString(updatedRatingOptions));
        }
        ratingRepository.save(rating);
    }

    @Override
    public List<UserRatingViewerDetailDTO> viewLoggedInUserRating() {
        List<Rating> userRatings = ratingRepository.findAllByUser_Id(userDetails.getCurrentUseId());
        return userRatings.stream()
                .map(e -> {
                    UserRatingViewerDetailDTO viewerDTO = new UserRatingViewerDetailDTO();
                    viewerDTO.setReceiverType(e.getDoctor() == null ? "HOSPITAL" : "DOCTOR");
                    viewerDTO.setReceiverId(e.getDoctor() == null ? e.getHospital().getId().toString() : e.getDoctor().getId().toString());
                    viewerDTO.setComments(e.getComments());
                    viewerDTO.setRatings(ratingOptionHandler.generateRatingFromExistence(e.getRating_options()));
                    return viewerDTO;
                }).toList();
    }

    @Override
    public void updateLoggedInUserRating(RatingViewerDTO updatedValue) {

    }

    @Override
    public RatingDetailViewerDTO viewDoctorRatings(int doctorId) {
        List<Rating> ratings = ratingRepository.findAllByDoctor_Id(doctorId);
        return generateDetailRating(ratings);
    }

    @Override
    public RatingDetailViewerDTO viewHospitalRatings(int hospitalId) {
        List<Rating> ratings = ratingRepository.findAllByHospital_Id(hospitalId);
        return generateDetailRating(ratings);
    }

    private RatingDetailViewerDTO generateDetailRating(List<Rating> ratings) {
        List<UserRatingWrapperDTO> userRatingWrappers = new ArrayList<>();
        for (Rating rating : ratings) {
            User user = userRepository.findById(rating.getUser().getId()).orElse(null);
            if (user == null) {
                log.info("user id -> {} not found!, may be deleted, so ignoring its rating!", rating.getUser().getId());
                continue;
            }

            List<RatingOptionDTO> userRatings = new ArrayList<>();
            List<RatingOptionDTO> userGivenRating = ratingOptionHandler.generateRatingFromExistence(rating.getRating_options());

            for (RatingOptionDTO ratingOption : userGivenRating) {
                RatingOptionDTO userRatingGeneratorDTO = new RatingOptionDTO();
                userRatingGeneratorDTO.setRating(ratingOption.getRating());
                userRatingGeneratorDTO.setTypeName(ratingOption.getTypeName());
                userRatingGeneratorDTO.setSource(ratingOption.getSource());
                userRatingGeneratorDTO.setDescription(ratingOption.getDescription());
                userRatingGeneratorDTO.setDescriptionBn(ratingOption.getDescriptionBn());

                userRatings.add(userRatingGeneratorDTO);
            }

            RatingViewerDTO ratingViewerDTO = new RatingViewerDTO();
            ratingViewerDTO.setComments(rating.getComments());
            ratingViewerDTO.setRatings(userRatings);

            UserRatingWrapperDTO userRatingWrapperDTO = new UserRatingWrapperDTO();
            userRatingWrapperDTO.setUserEmail(user.getEmail());
            userRatingWrapperDTO.setUserName(user.getUsername());
            userRatingWrapperDTO.setUserId(Integer.parseInt(String.valueOf(user.getId())));
            userRatingWrapperDTO.setUserRating(ratingViewerDTO);
            userRatingWrappers.add(userRatingWrapperDTO);
        }

        RatingDetailViewerDTO ratingDetailViewerDTO = new RatingDetailViewerDTO();
        ratingDetailViewerDTO.setOverallRating(calculateOverallRating(userRatingWrappers));
        ratingDetailViewerDTO.setRatingDetails(userRatingWrappers.isEmpty() ? generateDummy() : userRatingWrappers);

        return ratingDetailViewerDTO;
    }

    private List<UserRatingWrapperDTO> generateDummy() {
        List<RatingOptionDTO> userRatings = new ArrayList<>();
        List<RatingOptionDTO> userGivenRating = ratingOptionHandler.generateRating();

        for (RatingOptionDTO ratingOption : userGivenRating) {
            RatingOptionDTO userRatingGeneratorDTO = new RatingOptionDTO();
            userRatingGeneratorDTO.setRating(ratingOption.getRating());
            userRatingGeneratorDTO.setTypeName(ratingOption.getTypeName());
            userRatingGeneratorDTO.setSource(ratingOption.getSource());
            userRatingGeneratorDTO.setDescription(ratingOption.getDescription());
            userRatingGeneratorDTO.setDescriptionBn(ratingOption.getDescriptionBn());

            userRatings.add(userRatingGeneratorDTO);
        }

        RatingViewerDTO ratingViewerDTO = new RatingViewerDTO();
        ratingViewerDTO.setComments("");
        ratingViewerDTO.setRatings(userRatings);

        UserRatingWrapperDTO userRatingWrapperDTO = new UserRatingWrapperDTO();
        userRatingWrapperDTO.setUserEmail(userDetails.getCurrentUserEmail());
        userRatingWrapperDTO.setUserName(userDetails.getCurrentUserName());
        userRatingWrapperDTO.setUserId(userDetails.getCurrentUseId());
        userRatingWrapperDTO.setUserRating(ratingViewerDTO);

        List<UserRatingWrapperDTO> userRatingWrappers = new ArrayList<>();
        userRatingWrappers.add(userRatingWrapperDTO);

        return userRatingWrappers;
    }

    private double calculateOverallRating(List<UserRatingWrapperDTO> userRatingWrappers) {
        double totalNumberOfUser = userRatingWrappers.stream().map(UserRatingWrapperDTO::getUserId).count();
        double totalRatingSum = userRatingWrappers.stream()
                .map(UserRatingWrapperDTO::getUserRating)
                .map(RatingViewerDTO::getRatings)
                .flatMap(Collection::stream)
                .map(RatingOptionDTO::getRating)
                .mapToDouble(e -> e)
                .sum();
        if (totalNumberOfUser == 0) return 0;
        return totalRatingSum / totalNumberOfUser;
    }

    public void addRatingOptions(RatingOptionDTO ratingOptionDTO) {
        RatingOption ratingOption = new RatingOption();
        ratingOption.setTypeName(ratingOptionDTO.getTypeName());
        ratingOption.setSource(ratingOptionDTO.getSource());
        ratingOption.setDescription(ratingOptionDTO.getDescription());
        ratingOption.setDescriptionBn(ratingOptionDTO.getDescriptionBn());
        ratingOptionsRepository.save(ratingOption);
    }

    public void removeRatingOptions(String typeName) {
        ratingOptionsRepository.findByTypeName(typeName).ifPresent(ratingOptionsRepository::delete);
    }
}
