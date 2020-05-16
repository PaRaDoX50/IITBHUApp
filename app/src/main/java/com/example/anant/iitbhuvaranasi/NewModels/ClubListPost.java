package com.example.anant.iitbhuvaranasi.NewModels;

import androidx.annotation.Nullable;

public class ClubListPost {

    @Nullable
    private int id;

    @Nullable
    private String name;

    @Nullable
    private BuiltAllCouncilPost council;

    @Nullable
    private String small_image_url;

    @Nullable
    private String large_image_url;

    public ClubListPost(
            int id,
            @Nullable String name,
            @Nullable BuiltAllCouncilPost council,
            @Nullable String small_image_url,
            @Nullable String large_image_url
    ) {
        this.id = id;
        this.name = name;
        this.council = council;
        this.small_image_url = small_image_url;
        this.large_image_url = large_image_url;
    }
}
