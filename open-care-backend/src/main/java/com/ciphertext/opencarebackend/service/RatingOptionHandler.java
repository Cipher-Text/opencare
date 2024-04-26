package com.ciphertext.opencarebackend.service;

import com.ciphertext.opencarebackend.model.dto.ratings.RatingOptionDTO;
import com.ciphertext.opencarebackend.model.entity.RatingOption;
import com.ciphertext.opencarebackend.repository.RatingOptionsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class RatingOptionHandler {
    private final RatingOptionsRepository ratingOptionsRepository;

    public List<RatingOptionDTO> generateRating() {
        return ratingOptionsRepository.findAll()
                .stream().map(this::generateRatingOptionDTOFromRating).toList();
    }

    private RatingOptionDTO generateRatingOptionDTOFromRating(RatingOption e) {
        RatingOptionDTO dto = new RatingOptionDTO();
        dto.setTypeName(e.getTypeName());
        dto.setSource(e.getSource());
        dto.setDescription(e.getDescription());
        dto.setDescriptionBn(e.getDescriptionBn());
        dto.setRating(0);
        return dto;
    }

    @SneakyThrows
    public List<RatingOptionDTO> generateRatingFromExistence(String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        RatingOptionDTO[] existenceRatings = objectMapper.readValue(data, RatingOptionDTO[].class);
        List<RatingOption> ratingOptions = ratingOptionsRepository.findAll();

        return ratingOptions.stream()
                .map(ratingOption -> Arrays.stream(existenceRatings)
                        .filter(e -> e.getTypeName().equalsIgnoreCase(ratingOption.getTypeName()))
                        .findFirst()
                        .orElseGet(() -> generateRatingOptionDTOFromRating(ratingOption)))
                .collect(Collectors.toList());

    }

    public List<RatingOptionDTO> addOrUpdateRating(String data, List<RatingOptionDTO> modifiedList) {
        List<RatingOptionDTO> ratingOptionDTOS = generateRatingFromExistence(data);

        return ratingOptionDTOS.stream()
                .flatMap(ratingOption -> {
                    Optional<RatingOptionDTO> existing = modifiedList.stream()
                            .filter(e -> e.getTypeName().equalsIgnoreCase(ratingOption.getTypeName()))
                            .findFirst();
                    return existing.map(Stream::of).orElseGet(() -> Stream.of(ratingOption));
                }).collect(Collectors.toList());
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
