drop table if exists track_genres;
drop table if exists track;
drop sequence if EXISTS track_seq;

create sequence IF NOT EXISTS track_seq start with 1 increment by 50;

CREATE TABLE IF NOT EXISTS track
(
    track_id bigint NOT NULL,
    title varchar(255),
    author varchar(255),
    album varchar(255),
    duration_sec integer,
    release_date date,
    comment varchar(255),
    file_path varchar(255) NOT NULL,
    file_name varchar(255) NOT NULL,
    PRIMARY KEY (track_id)
);

CREATE TABLE IF NOT EXISTS track_genres
(
    track_id bigint NOT NULL,
    genre varchar(255) DEFAULT NULL,
    FOREIGN KEY (track_id) REFERENCES track (track_id)
);