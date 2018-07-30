DELETE FROM member;

INSERT INTO member (
  name
, yomigana
, division
, floor
, extensionnumber
)
VALUES (
  'piyo　hoge'
, 'piyo'
, '管理部'
, '1階南'
, '1111'
)
, (
  'hoge　piyo'
, 'hoge'
, '医療'
, '3F南'
, '3333'
)
, (
  'piyo　fuga'
, 'piyo'
, 'ITS'
, '4F北'
, '4444'
)
, (
  'hoge　fuga'
, 'hoge'
, 'ITS'
, '4F南'
, '4455'
)
, (
  'fuga　piyo'
, 'fuga'
, '金融'
, '2F南'
, '2222'
)
;
