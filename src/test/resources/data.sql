INSERT INTO division (
  name
, div
)
SELECT
  '管理部'
, 'adm'
WHERE
  NOT EXISTS (
    SELECT
      name
    FROM
      division
    WHERE
      name = '管理部'
  )
;

INSERT INTO division (
  name
, div
)
SELECT
  'アジャイル'
, 'ag'
WHERE
  NOT EXISTS (
    SELECT
      name
    FROM
      division
    WHERE
      name = 'アジャイル'
  )
;

INSERT INTO division (
  name
, div
)
SELECT
  'ITS'
, 'its'
WHERE
  NOT EXISTS (
    SELECT
      name
    FROM
      division
    WHERE
      name = 'ITS'
  )
;

INSERT INTO division (
  name
, div
)
SELECT
  '金融'
, 'fin'
WHERE
  NOT EXISTS (
    SELECT
      name
    FROM
      division
    WHERE
      name = '金融'
  )
;

INSERT INTO division (
  name
, div
)
SELECT
  '医療'
, 'med'
WHERE
  NOT EXISTS (
    SELECT
      name
    FROM
      division
    WHERE
      name = '医療'
  )
;

INSERT INTO division (
  name
, div
)
SELECT
  'ETEC'
, 'etec'
WHERE
  NOT EXISTS (
    SELECT
      name
    FROM
      division
    WHERE
      name = 'ETEC'
  )
;

INSERT INTO division (
  name
, div
)
SELECT
  '未来企画室'
, 'fpo'
WHERE
  NOT EXISTS (
    SELECT
      name
    FROM
      division
    WHERE
      name = '未来企画室'
  )
;

INSERT INTO division (
  name
, div
)
SELECT
  '社長室'
, 'ceo'
WHERE
  NOT EXISTS (
    SELECT
      name
    FROM
      division
    WHERE
      name = '社長室'
  )
;

DELETE FROM member;

INSERT INTO member (
  name
, yomigana
, division_id
, floor
, extensionnumber
)
VALUES (
  'piyo　hoge'
, 'piyo'
, '1'
, '1階南'
, '1111'
)
, (
  'hoge　piyo'
, 'hoge'
, '5'
, '3F南'
, '3333'
)
, (
  'piyo　fuga'
, 'piyo'
, '3'
, '4F北'
, '4444'
)
, (
  'hoge　fuga'
, 'hoge'
, '3'
, '4F南'
, '4455'
)
, (
  'fuga　piyo'
, 'fuga'
, '4'
, '2F南'
, '2222'
)
;
