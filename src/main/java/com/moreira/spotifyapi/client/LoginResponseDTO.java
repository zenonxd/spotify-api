package com.moreira.spotifyapi.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginResponseDTO {

    @JsonProperty("access_token")
    private String accessToken;

}


