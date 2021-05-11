package com.urlshortener.app.config;

import java.util.UUID;

public class Constant {
    // The minimum length of the generated short URL (not including domain name path)
    public static final Integer MIN_SHORT_URL_ID_LENGTH = 3;
    // Create a fake user id for the app due to we don't want to implement user authentication yet
    public static final UUID FAKE_USER_ID = UUID.fromString("7d2dd346-8ebe-4f7b-a969-c4b8242725b4");
    public static final Integer MAX_URL_CONFLICT_ROUND_CHECK_RATE = 100;
    public static final Integer MAX_URL_CONFLICT_ROUND_CHECK_PER_LENGTH_RATE = 50;
}
