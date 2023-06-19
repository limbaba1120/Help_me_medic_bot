INSERT INTO users(country, login_id, name, nickname, password)
VALUES ('South Korea', 'admin', 'admin', 'Admin', 'admin'),
       ('test1', 'test1', 'test1', '中村 あげは', 'Japan'),
       ('test2', 'test2', 'test2', '조병찬', 'South Korea');

INSERT INTO posts(content, created_at, updated_at, user_id)
VALUES ('안녕하세요? 포럼 테스트용 게시물입니다. 포럼은 챗봇으로 해결되지 않는 문제를 집단지성으로 해결할 수 있는 공간입니다.', NOW(), NOW(), 1),
       ('頭がとても痛いのですが、どこに行けばいいですか?', NOW(), NOW(), 2);

INSERT INTO comments(content, created_at, updated_at, post_id, user_id)
VALUES ('こんにちは', NOW(), NOW(), 1, 2),
       ('반갑습니다.', NOW(), NOW(), 1, 3);
