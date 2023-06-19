package com.capstonedesign.backend.domain.map.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceSearchResponseByGoogle {

  private List<Result> results;
  private String status;

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Result {

    private String business_status;
    private Geometry geometry;
    private String icon;
    private String icon_background_color;
    private String icon_mask_base_uri;
    private String name;
    private String place_id;
    private PlusCode plus_code;
    private Double rating;
    private String reference;
    private String scope;
    private List<String> types;
    private Integer user_ratings_total;
    private String vicinity;
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  static class Geometry {

    private Location location;
    private Viewport viewport;
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  static class Location {

    private Double lat;
    private Double lng;
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  static class Viewport {

    private Northeast northeast;
    private Southwest southwest;
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  static class Northeast {

    private Double lat;
    private Double lng;
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  static class Southwest {

    private Double lat;
    private Double lng;
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  static class PlusCode {

    private String compound_code;
    private String global_code;
  }
}