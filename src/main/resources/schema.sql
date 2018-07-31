CREATE TABLE IF NOT EXISTS division (
  id serial PRIMARY KEY
, name varchar(20) NOT NULL
, div varchar(20) NOT NULL
)
;

CREATE TABLE IF NOT EXISTS member (
  id serial PRIMARY KEY
, name varchar(20) NOT NULL
, yomigana varchar(20) NOT NULL
, division_id integer NOT NULL
, floor varchar(20) NOT NULL
, extensionnumber char(4) NOT NULL
)
;
