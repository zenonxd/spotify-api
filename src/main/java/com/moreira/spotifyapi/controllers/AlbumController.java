package com.moreira.spotifyapi.controllers;


import com.moreira.spotifyapi.client.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("spotify/api")
public class AlbumController {

    @Value("${clientId}")
    private String clientId;

    @Value("${clientSecret}")
    private String clientSecret;

    private final AuthSpotifyClient authSpotifyClient;
    private final AlbumSpotifyClient albumSpotifyClient;

    public AlbumController(AuthSpotifyClient authSpotifyClient, AlbumSpotifyClient albumSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
        this.albumSpotifyClient = albumSpotifyClient;
    }

    @GetMapping("/token")
    public ResponseEntity<String> getToken() {
        var request = new LoginRequestDTO(
                "client_credentials",
                clientId,
                clientSecret
        );

        return ResponseEntity.ok(authSpotifyClient.login(request).getAccessToken());
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> getNewReleases() {
        var request = new LoginRequestDTO(
                "client_credentials",
                clientId,
                clientSecret
        );
        var token = authSpotifyClient.login(request).getAccessToken();

        var response = albumSpotifyClient.getNewReleases("Bearer " + token);

        return ResponseEntity.ok(response.getAlbums().getItems());
    }
}
