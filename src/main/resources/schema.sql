-- noinspection SqlNoDataSourceInspectionForFile
CREATE TABLE IF NOT EXISTS link
(
    id UUID NOT NULL,
    url varchar(256) NOT NULL,
    short_url varchar(256) NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    user_id UUID,
    UNIQUE(short_url),
    UNIQUE(url),
    UNIQUE(short_url, url),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS setting
(
    id UUID NOT NULL,
    domain varchar(256) NOT NULL,
    user_id UUID,
    PRIMARY KEY (id)
);