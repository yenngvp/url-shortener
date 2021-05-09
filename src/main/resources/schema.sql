-- noinspection SqlNoDataSourceInspectionForFile
CREATE TABLE IF NOT EXISTS Link
(
    id UUID NOT NULL,
    url varchar(256) NOT NULL,
    shortUrl varchar(256) NOT NULL,
    createdDate date DEFAULT CURRENT_DATE,
    userId bigint default 1,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Setting
(
    id UUID NOT NULL,
    domain varchar(256) NOT NULL,
    userId bigint default 1,
    PRIMARY KEY (id)
);