package com.capstonedesign.backend.domain.map.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceSearchResponseSortedByRating {

  private String business_state;

  private PlaceSearchResponseByGoogle.Geometry geometry;

  private String name;

  private String place_id;

  private Double rating;

  private Integer user_ratings_total;

  private String vicinity;

}

@Getter
@NoArgsConstructor
@AllArgsConstructor
class Geometry {

  private Location location;
  private Viewport viewport;
}

@Getter
@NoArgsConstructor
@AllArgsConstructor
class Location {

  private Double lat;
  private Double lng;
}

@Getter
@NoArgsConstructor
@AllArgsConstructor
class Viewport {

  private Northeast northeast;
  private Southwest southwest;
}

@Getter
@NoArgsConstructor
@AllArgsConstructor
class Northeast {

  private Double lat;
  private Double lng;
}

@Getter
@NoArgsConstructor
@AllArgsConstructor
class Southwest {

  private Double lat;
  private Double lng;
}
