CREATE TABLE IF NOT EXISTS member (
  id serial PRIMARY KEY
, name varchar(20) NOT NULL
, furigana varchar(20) NOT NULL
, division varchar(20) NOT NULL
, floor varchar(20) NOT NULL
, extensionnumber char(4) NOT NULL
)
;
