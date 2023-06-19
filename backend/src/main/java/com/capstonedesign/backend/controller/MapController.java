package com.capstonedesign.backend.controller;

import com.capstonedesign.backend.domain.map.dto.request.PlaceSearchRequest;
import com.capstonedesign.backend.domain.map.dto.response.PlaceSearchResponseByCustom;
import com.capstonedesign.backend.domain.map.dto.response.PlaceSearchResponseByGoogle;
import com.capstonedesign.backend.domain.map.dto.response.PlaceSearchResponseSortedByRating;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"https://xn--2z1bo3hjrs.xn--yq5b.xn--3e0b707e/"})
@Api(tags = "지도 관련 API")
public class MapController {

  @PostMapping("/place")
  @ApiOperation(value = "장소 검색 API", notes = "검색어, 장소 유형, 반경과 위도 경도 등을 요청 파라미터로 하여 장소 검색")
  public PlaceSearchResponseByCustom<List<PlaceSearchResponseSortedByRating>> searchPlace(@RequestBody PlaceSearchRequest request) {

    String key = "AIzaSyD8kuBD0jS2AmkmbSPnl6ONEg5OrW55Spk";

    /*
      장소 목록 검색
     */
    String apiURL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" +
        "?location=" + request.getLatitude() + "," + request.getLongitude() +
        "&radius=" + request.getRadius() +
        "&type=" + request.getType() +
        "&keyword=" + request.getKeyword() +
        "&key=" + key;

    /*
      단일 장소 검색
     */
//    String apiURL2 = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json" +
//        "?input=" + request.getKeyword() +
//        "&locationbias=circle:" + request.getRadius() + "@" + request.getLatitude() + "," + request.getLongitude() +
//        "&fields=formatted_address,name,rating,opening_hours,place_id,geometry,type" +
//        "&inputtype=textquery" +
//        "&language=ko" +
//        "&key=" + key;

    /*
      단일 장소 세부정보 검색
     */
//    String place_id = "ChIJZUnrlhNaezUR-VgFZQhPLeg";
//    String apiURL3 =
//        "https://maps.googleapis.com/maps/api/place/details/json" +
//            "?place_id=" + place_id +
//            "&fields=name,rating,formatted_address,geometry,opening_hours,rating,reviews" +
//            "&language=ko" +
//            "&key=" + key;

    // 장소 목록 검색 API 요청
    WebClient client = WebClient.builder()
        .baseUrl(apiURL)
        .defaultHeader(HttpHeaders.ACCEPT_LANGUAGE, "ko-KR") // accept-language 헤더 추가
        .build();

    Mono<PlaceSearchResponseByGoogle> response = client.get()
        .uri(apiURL)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<>() {
        });

    // 장소 목록 평점 높은 순으로 정렬
    List<PlaceSearchResponseByGoogle.Result> results = Objects.requireNonNull(response.block()).getResults();

    // 검색 결과 리스트를 평점에 따라 내림차순 정렬
    List<PlaceSearchResponseByGoogle.Result> sortedResults = results.stream()
        .sorted(Comparator.comparing(PlaceSearchResponseByGoogle.Result::getRating, Comparator.nullsLast(Double::compareTo)).reversed())
        .collect(Collectors.toList());

    // 평점에 따라 정렬된 데이터에서 필요한 항목만 뽑아 새로운 response DTO에 매핑
    List<PlaceSearchResponseSortedByRating> sortedResultsResponse = sortedResults.stream()
        .map(result -> new PlaceSearchResponseSortedByRating(
            result.getBusiness_status(), result.getGeometry(), result.getName(), result.getPlace_id(), result.getRating(), result.getUser_ratings_total(), result.getVicinity()))
        .collect(Collectors.toList());

    return new PlaceSearchResponseByCustom<>(sortedResultsResponse.size(), sortedResultsResponse);
  }
}